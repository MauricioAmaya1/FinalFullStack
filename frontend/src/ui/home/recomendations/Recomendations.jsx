import { HomeContext } from '../../../components/utils/home.context';

import styles from './recomendations.module.css';

import { useContext, useEffect } from 'react';

import { handleFetchGet } from '../../../functions/handleFetch';
import { urlBase } from '../../../utils/apiUrl.json'

import LoadingScreen from '../../template/loading/LoadingScreen';
import HotelsList from './hotels_list/HotelsList';

const Recomendations = () => {

    const {
        recomendationsData,
        isLoading,

        setters: {
            setRecomendationsData,
            setIsLoading
        }
    } = useContext(HomeContext)

    useEffect(() => {
        handleFetchGet(`${urlBase}/productos/random`, setRecomendationsData, setIsLoading, null, null)
    }, [])

    return (
        <div className={styles.recomendations}>
            {isLoading ? <LoadingScreen /> : (
                <div className='container'>
                    <div className={`row ${styles.contentContainer}`}>
                        <h2 className={styles.title}>Recomendaciones</h2>
                        {recomendationsData ? <HotelsList data={recomendationsData} /> : (

                            <div>
                                No hay productos disponibles para estos filtros
                            </div>

                        )}
                    </div>
                </div>
            )}
        </div>
    );

}

export default Recomendations;