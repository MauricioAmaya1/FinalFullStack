import { HomeContext } from '../../../../components/utils/home.context';

import { useContext } from 'react';

import styles from './search_form.module.css';

import CitySelector from './city_selector/CitySelector';
import DateSelector from './date_selector/DateSelector';

import { handleFetchGet } from '../../../../functions/handleFetch';
import { urlBase } from '../../../../utils/apiUrl.json'

const SearchForm = () => {

    const {
        selectedCity,
        completeStartDate,
        completeEndDate,

        setters: { setRecomendationsData },
        setters: { setIsLoading },
    } = useContext(HomeContext)

    const handleFilterCategory = () => {

        if (selectedCity && completeStartDate && completeEndDate) {

            handleFetchGet(`${urlBase}/productos/dateCiudad?fechaInicio=${completeStartDate}&fechaFinal=${completeEndDate}&ciudadId=${selectedCity.value}`, setRecomendationsData, setIsLoading, null, null)

        } else if (selectedCity) {

            handleFetchGet(`${urlBase}/productos/ciudad/${selectedCity.value}`, setRecomendationsData, setIsLoading, null, null)

        } else if (completeStartDate && completeEndDate) {

            handleFetchGet(`${urlBase}/productos/date?fechaInicio=${completeStartDate}&fechaFinal=${completeEndDate}`, setRecomendationsData, setIsLoading, null, null)
        }

    }

    return (
        <div className={styles.search_form}>
            <CitySelector />
            <DateSelector />
            <button onClick={handleFilterCategory} className="submit">Buscar</button>
        </div>
    )
}

export default SearchForm;