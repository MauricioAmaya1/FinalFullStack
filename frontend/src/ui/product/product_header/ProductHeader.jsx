import styles from './productHeader.module.css'

import { useState } from 'react'
import { useNavigate } from 'react-router-dom'

import HotelRating from '../../../components/hotelRating/HotelRating'

const ProductHeader = ({ data }) => {

    const navigate = useNavigate()

    const [isFromStorage, setIsFromStorage] = useState(() => {
        if (data.name) {
            return true
        } else {
            return false
        }
    })

    return (
        <div className={styles.productHeader}>
            <div className={`${styles.titleSection} ${styles.content}`}>
                <div className='container'>
                    <div className='row'>
                        <div className={styles.titleSectionContent}>
                            <div className={styles.titleContainer}>
                                <h3>{isFromStorage ? data.category.name : data.categoria.titulo}</h3>
                                <h2 className={styles}>{isFromStorage ? data.name : data.titulo}</h2>
                            </div>
                            <img onClick={() => navigate(-1)} className={styles.backToHome} src="/backToHome.svg" alt="Back to home" />
                        </div>
                    </div>
                </div>
            </div>
            <div className={styles.locationSection}>
                <div className='container'>
                    <div className='row'>
                        <div className={styles.locationSectionContent}>
                            <div className={styles.locationContainer}>
                                <img className={styles.locationLogo} src="/icons/location_icon.svg" alt="Location icon" />
                                <div className={styles.locationText}>
                                    <p>{isFromStorage ? data.city.name : data.ciudad.nombre_ciudad}, {isFromStorage ? data.city.country : data.ciudad.nombre_pais}</p>
                                    <p className={styles.distance}>{isFromStorage ? data.centerDistance : data.distanciaCentro}</p>
                                </div>
                            </div>
                            <div className={styles.scoreContainer}>
                                <div className={styles.qualityContainer}>
                                    <p className={styles.quality}>{isFromStorage ? data.quality : data.review}</p>
                                    <HotelRating rating={isFromStorage ? data.stars : data.estrellas} />
                                </div>
                                <p className={styles.score}>{isFromStorage ? data.points : data.puntuacion}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ProductHeader