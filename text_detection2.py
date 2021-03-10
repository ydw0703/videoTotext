# -*- coding: utf-8 -*-
import cv2
import numpy as np

def selectWords(img):
    # org = cv2.imread('capture4.png', cv2.IMREAD_COLOR)
    org = img
    
    # org = cv2.resize(org, dsize=(0,0), fx=0.5, fy=0.5)
    gray = cv2.cvtColor(org, cv2.COLOR_BGR2GRAY)  # ================  1 gray scale로 변환

    kernel = np.ones((50, 2), np.uint8) #행,열의 사이즈
    # result = cv2.erode(org, kernel, iterations = 1)
    kernel2 = np.ones((5, 5), np.uint8) #행,열의 사이즈
    roi_list = []

    morph = cv2.morphologyEx(gray, cv2.MORPH_GRADIENT, kernel)  # 2 ================ 경계선 찾기

    thr = cv2.adaptiveThreshold(morph, 255, cv2.ADAPTIVE_THRESH_MEAN_C,  cv2.THRESH_BINARY_INV, 11, 20)  # 3 ================ 임계처리 

    morph2 = cv2.morphologyEx(thr, cv2.MORPH_CLOSE, kernel2)  # 4 ================ 뭉게기

    contours, _ = cv2.findContours(morph2, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)  # 5 ================ 특징점 찾기

    org2 = cv2.copyMakeBorder(org, 0, 0, 0, 0, cv2.BORDER_REPLICATE)
    for cnt in contours:
        try:
            x, y, w, h = cv2.boundingRect(cnt)
            if 50< w <200 and 85 < h < 200: # ============= 너비가 상수범위, 높이가 상수 범위인 값에 대하여
                # print(w, h)
                roi = org2[y:y + h, x:x + w]
                # cv2.imshow('roi', roi)
                roi_list.append(roi)
                cv2.rectangle(org, (x, y), (x+w, y+h), (0, 0, 255), 2)

        except Exception as e:
           pass

    cnt = 0              # print all pieces
    '''for r in roi_list:
        cnt += 1
        cv2.imshow(str(cnt), r)'''
    org3 = cv2.resize(org, (960,540))
    # gray2 = cv2.resize(gray, (960,540))
    # morph3 = cv2.resize(morph, (960,540))
    morph4 = cv2.resize(morph2, (960,540))
    thr2 = cv2.resize(thr, (960,540))
    # result2 = cv2.resize(result, (960,540))
    # cv2.imshow('result',result2)
    cv2.imshow('org', org3)
    # cv2.imshow('gray', gray2)
    # cv2.imshow('morph', morph3)
    cv2.imshow('morph2', morph4)
    cv2.imshow('thr', thr2)

    return org, roi_list, gray, morph, morph2, thr


# srcfile='C:\\Users\\Administrator\\Desktop\\2021_ICT\\test_photo.jpg'

# image=cv2.imread(srcfile)
# img = np.array(image)
# selectWords(img)
# cv2.waitKey(0)
# img.release()
# cv2.destroyAllWindows()
