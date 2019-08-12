import video_process as vp
import image_process as ct 
import imgae_reco as reco
import text_process as txt
import cv2


def main():
# ============== 하나 이미지 테스트 =============
    # fianl_result_array=[]
    # final_result=''
    # d = ct.read_configs('configs.json')
    # # video_path='test_video/amazing_720p.mp4'
    # # frame_images = vp.extract_image_fps(video_path)
    # frame_images = cv2.imread('test_video/kang4.png')
    # copy = ct.resize(frame_images)

    # cropped_images=ct.image_all_process(copy)
    # for con in cropped_images:
    #     result = reco.extract_text(con)
    #     final_result = final_result + txt.text_pre_process(result)
    # fianl_result_array.append(final_result)
    # txt.text_save(fianl_result_array, 'amazing_output.csv')

# =================== 동영상 테스트 ================================
    fianl_result_array=[]
    d = ct.read_configs('configs.json')
    video_path='test_video/amazing_720p.mp4'
    frame_images = vp.extract_image_fps(video_path)
    for i, frame in enumerate(frame_images):
        vp.save_image(frame,i)
        final_result=''
        copy = ct.resize(frame)
        cropped_images=ct.image_all_process(copy)
        for con in cropped_images:
            result = reco.extract_text(con)
            final_result = final_result + txt.text_pre_process(result)
        fianl_result_array.append(final_result)
        
    txt.text_save(fianl_result_array, 'amazing_output.csv')
    # for frame in frame_images:
    #     final_result=''
    #     i = 0
    #     cropped_images = ct.image_all_process(frame)
    #     for con in cropped_images :
    #         vp.save_image(con, "/croped/crop_" + str(i))
    #         result = reco.extract_text(con)
    #         final_result = final_result + txt.text_pre_process(result)
            
    #     fianl_result_array.append(final_result)
    #     i += 1
    # txt.text_save(fianl_result_array, 'amazing_output.csv')



if __name__ == "__main__":
    main()