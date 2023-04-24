import CategoriesList from './categories_list/CategoriesList';

import styles from './categories.module.css';

const Categories = () => {

    return (
        <div className={styles.categories}>
            <div className={`container ${styles.categoriesContainer}`}>
                <div className={"row"}>
                    <h2 className={styles.title}>Buscar por tipo de alojamiento</h2>
                    <CategoriesList />
                </div>
            </div>
        </div>
    );

}

export default Categories;