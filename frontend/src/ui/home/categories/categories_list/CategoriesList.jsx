import styles from './categories_list.module.css';

import Category from './category/Category';

import { urlBase } from '../../../../utils/apiUrl.json'

import { useFetch } from '../../../../customHooks/useFetch';
import LoadingScreen from '../../../template/loading/LoadingScreen';

const CategoriesList = () => {

    const { data: categories, loading } = useFetch(`${urlBase}/categorias`)
    const { data: categoriesQuantity } = useFetch(`${urlBase}/categorias/cantidad`)

    return (
        <div className={styles.categoriesList}>
            {loading ? <LoadingScreen /> : (
                categories?.map((category) => <Category key={category.id} id={category.id} name={category.titulo} image={category.urlImagen} categoryQuantity={categoriesQuantity[category.id - 1]} />)
            )}
        </div>
    );

}

export default CategoriesList;