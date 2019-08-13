import json
import cv2
from PIL import Image
import numpy as np 



def read_configs(json_file):
	with open('configs.json', 'r', encoding='utf-8') as outfile:
		d = json.load(outfile)
	global configs 
	configs = d
	
	return d


def resize(image, flag=-1):
	global configs
	standard_height = configs['resize_origin']['standard_height']
	standard_width = configs['resize_origin']['standard_width']

	height, width = image.shape[:2]
	image_copy = image.copy()
	# print original size (width, height)
	print("origin (width : " + str(width) + ", height : " + str(height) + ")")
	rate = 1  # default
	if (flag < 0 and height < standard_height) or (flag < 0 and height > standard_height):  # Resize based on height
		rate = standard_height / height
	elif (flag < 0 and width < standard_width) or (flag < 0 and height > standard_height):  # Resize based on width
		rate = standard_width / width

	# resize
	w = round(width * rate)  # should be integer
	h = round(height * rate)  # should be integer
	image_copy = cv2.resize(image_copy, (w, h))
	# print modified size (width, height)
	print("after resize : (width : " + str(w) + ", height : " + str(h) + ")")
	return image_copy


def get_gray(image_origin):
	copy = image_origin.copy()
	image_gray = cv2.cvtColor(copy, cv2.COLOR_BGR2GRAY)
	return image_gray

def get_canny(image_gray):
	copy = image_gray.copy()
	kernel_size = 5
	blur_gray = cv2.GaussianBlur(copy, (3, 3), 0)
	low_threshold = 50
	high_threshold = 150
	edges = cv2.Canny(blur_gray, low_threshold, high_threshold)
	return edges

def get_gradient(image_gray):
	copy = image_gray.copy()

	global configs
	kernel_size_row = configs['gradient']['kernel_size_row']
	kernel_size_col = configs['gradient']['kernel_size_col']

	kernel = cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (kernel_size_row, kernel_size_col))

	image_gradient = cv2.morphologyEx(copy, cv2.MORPH_GRADIENT, kernel)
	return image_gradient

def get_threshold(image_gray): 
	copy = image_gray.copy()

	global configs
	mode = configs['threshold']['mode']
	block_size = configs['threshold']['block_size']
	subtract_val = configs['threshold']['subtract_val']

	if mode == 'mean':
		image_threshold = cv2.adaptiveThreshold(copy, 255, 
			cv2.ADAPTIVE_THRESH_MEAN_C, cv2.THRESH_BINARY_INV, block_size, subtract_val)
	elif mode == 'gaussian':
		image_threshold = cv2.adaptiveThreshold(copy, 255, 
			cv2.ADAPTIVE_THRESH_GAUSSIAN_C, cv2.THRESH_BINARY_INV, block_size, subtract_val)
	else: 
		image_threshold = get_otsu_threshold(copy)

	return image_threshold

def get_otsu_threshold(image_gray):
	copy = image_gray.copy()
	blur = cv2.GaussianBlur(copy, (5, 5), 0)
	ret3, imgae_otsu = cv2.threshold(copy, 0, 255, cv2.THRESH_BINARY + cv2.THRESH_OTSU)
	return imgae_otsu


def remove_long_line(image_binary):
	copy = image_binary.copy()

	global configs 
	threshold = configs['remove_line']['threshold']
	min_line_length = configs['remove_line']['min_line_length']
	max_line_gap = configs['remove_line']['max_line_gap']

	lines = cv2.HoughLinesP(copy, 1, np.pi / 180, threshold, np.array([]), min_line_length, max_line_gap)
	if lines is not None: 
		for line in lines:
			x1, y1, x2, y2 = line[0]
			cv2.line(copy, (x1, y1), (x2, y2), (0, 0, 0), 2)
	return copy 

def get_closing(image_gray):
	copy = image_gray.copy()
	global configs

	kernel_size_row = configs['close']['kernel_size_row']
	kernel_size_col = configs['close']['kernel_size_col']

	kernel = cv2.getStructuringElement(cv2.MORPH_RECT, (kernel_size_row, kernel_size_col))

	image_close = cv2.morphologyEx(copy, cv2.MORPH_CLOSE, kernel)
	return image_close


def get_contours(image):

	global configs
	retrieve_mode = configs['contour']['retrieve_mode']
	approx_method = configs['contour']['approx_method']

	contours, hierachy= cv2.findContours(image, cv2.RETR_TREE, cv2.CHAIN_APPROX_TC89_KCOS)
	# _, contours = cv2.findContours(image, retrieve_mode, approx_method)

	# print(hierachy)
	
	return contours

def draw_contour_rect(image_origin, contours):
	rgb_copy = image_origin.copy()

	global configs 
	min_width = configs['contour']['min_width']
	min_height = configs['contour']['min_height']

	if len(contours) == 0:
		print('contours: 0')
		return image_copy
	else : 
		for contour in contours:
			rect = cv2.minAreaRect(contour)
			box = cv2.boxPoints(rect)
			box = np.int0(box)
			# print(box[0])
			cv2.drawContours(rgb_copy, [box], 0, (0, 255, 0), 2)

	return rgb_copy



def get_cropped_images(image_origin, contours):
	image_copy = image_origin.copy()
	global configs
	min_width = configs['contour']['min_width']
	min_height = configs['contour']['min_height']
	padding = 1
	origin_height, origin_width = image_copy.shape[:2]
	cropped_images = [] 

	for contour in contours:  # Crop the screenshot with on bounding rectangles of contours
		x, y, width, height = cv2.boundingRect(contour)  # top-left vertex coordinates (x,y) , width, height
		# screenshot that are larger than the standard size
		
		if width > min_width and height > min_height:
		# The range of row to crop (with padding)
			row_from = (y - padding) if (y - padding) > 0 else y
			row_to = (y + height + padding) if (y + height + padding) < origin_height else y + height
			# The range of column to crop (with padding)
			col_from = (x - padding) if (x - padding) > 0 else x
			col_to = (x + width + padding) if (x + width + padding) < origin_width else x + width
			# Crop the image with Numpy Array
			cropped = image_copy[row_from: row_to, col_from: col_to]
			cropped_images.append(cropped)  # add to the list
	return cropped_images




def image_all_process(imgae_file):
	gray = get_gray(imgae_file)
	cv2.imshow('gray', gray)

	# canny = get_canny(gray)
	# cv2.imshow('canny', canny)

	gray1 = get_gradient(gray)
	cv2.imshow('gray1', gray1)

	gray2 = get_threshold(gray1)
	cv2.imshow('gray2', gray2)

	gray3 = get_closing(gray2)
	cv2.imshow('gray3', gray3)

	gray4 = remove_long_line(gray3)
	cv2.imshow('gray4', gray4)

	contours = get_contours(gray4)
	print(len(contours))

	cv2.imshow('All contours', draw_contour_rect(imgae_file, contours))

	cv2.waitKey(0)
	cv2.destroyAllWindows()

	return get_cropped_images(imgae_file, contours)



if __name__ == '__main__':
	pass







