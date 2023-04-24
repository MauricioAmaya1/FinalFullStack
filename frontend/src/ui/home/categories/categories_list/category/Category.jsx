import { HomeContext } from '../../../../../components/utils/home.context';

import styles from './category.module.css';

import { useContext } from 'react';

import { handleFetchGet } from '../../../../../functions/handleFetch';
import { urlBase } from '../../../../../utils/apiUrl.json'

const Category = ({ id, name, image, categoryQuantity }) => {

    const { setters: { 
        setRecomendationsData,
        setIsLoading
     } } = useContext(HomeContext)

    const handleFilterCategory = () => {
        handleFetchGet(`${urlBase}/productos/categoria/${id}`, setRecomendationsData, setIsLoading, null, null)
    }

    return (
        <div onClick={handleFilterCategory} className={styles.category}>
            <div className={styles.categoryImage} style={{ backgroundColor: "red", backgroundImage: `url(${image})` }} />
            <div className={styles.categoryDescription}>
                <h2>{name}</h2>
                <span>{categoryQuantity} Disponibles</span>
            </div>
        </div>
    )

}

export default Category;