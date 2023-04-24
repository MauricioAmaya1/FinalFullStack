import styles from './product_images.module.css';

import { useState } from 'react';

import ImageGallery from 'react-image-gallery';

const ProductImages = ({ images }) => {

    const mainImage = images[0];
    const secondaryImages = images.slice(1);

    const [sliderVisible, setSliderVisible] = useState(false);

    const handleOpenGallery = () => {

        if (!sliderVisible) {
            setSliderVisible(true)
            document.body.style.overflow = "hidden"
        } else {
            setSliderVisible(false)
            document.body.style.overflow = "auto"
        }
    }

    return (
        <>
            <div className='container'>
                <div className='row'>
                    <div className={styles.productImagesVisibility}>
                        <div className={styles.productImages}>
                            <div className={styles.mainImage}>
                                <div className={styles.imageItem} style={{ backgroundImage: `url(${mainImage})` }}></div>
                            </div>
                            <div className={styles.secondaryImages}>
                                {
                                    secondaryImages.map((image, id) => <div key={id} className={styles.imageItem} style={{ backgroundImage: `url(${image})` }} />)
                                }
                            </div>
                            <button className={styles.viewMore} onClick={handleOpenGallery}>Ver más</button>
                        </div>
                        <div className={styles.imageGalleryWrapper} style={{ display: sliderVisible ? 'flex' : 'none' }}>
                            <div className={styles.imageGallery}>
                                <ImageGallery items={images.map((image) => {
                                    return {
                                        original: image,
                                        originalHeight: "500px",
                                        originalWidth: "500px",
                                    };
                                })} showPlayButton={false} showFullscreenButton={false} showBullets={true} />
                                <button className={styles.closeButton} onClick={handleOpenGallery}>
                                    <img src='/icons/close_icon.svg' width={30} />
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div className={styles.productImagesSliderVisibility}>
                <ImageGallery items={images.map((image) => {
                    return {
                        original: image,
                    };
                })} showPlayButton={false} showFullscreenButton={false} showBullets={true} autoPlay={true} />
            </div>
        </>
    )

}

export default ProductImages;