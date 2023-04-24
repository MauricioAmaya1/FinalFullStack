import SearchForm from './search_form/SearchForm';

import styles from './search.module.css';

function Search() {
    
    return (
        <div className={styles.search}>
            <div className={styles.searchContent}>
                <div className='container'>
                    <div className='row'>
                        <h1>Busca ofertas en hoteles, casas y mucho más</h1>
                        <SearchForm />
                    </div>
                </div>
            </div>
        </div>
    );

}

export default Search;