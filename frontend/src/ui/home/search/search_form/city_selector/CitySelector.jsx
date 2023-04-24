import { HomeContext } from '../../../../../components/utils/home.context';

import styles from './city_selector.module.css';

import { useContext } from 'react';

import Select, { components } from 'react-select';

import { useFetch } from '../../../../../customHooks/useFetch';

import { urlBase } from "../../../../../utils/apiUrl.json"

const CitySelector = () => {

    const { data } = useFetch(`${urlBase}/ciudades`)

    const { selectedCity,

        setters: {
            setSelectedCity
        }
    } = useContext(HomeContext)

    const selectCity = (value) => {
        setSelectedCity(value)
    }

    const options = data?.map(value => {
        return {
            value: value.id,
            label: <div><div>{value.nombre_ciudad}</div></div>,
        }
    });

    const SingleValue = ({ children, ...props }) => {
        const value = props.getValue()[0].label;
        return (
            <components.SingleValue {...props} className={styles.citySelectorSingleValue}>
                <img src="/icons/location_icon.svg" alt="Ubicación" />
                <span>{value}</span>
            </components.SingleValue>
        );
    }

    const Option = ({ children, ...props }) => {
        return (
            <components.Option {...props} className={styles.citySelectorOption}>
                <img src="/icons/location_icon.svg" alt="Ubicación" />
                <span>{children}</span>
            </components.Option>
        );
    }

    const Placeholder = ({ children, ...props }) => {
        return (
            <components.Placeholder {...props} className={styles.citySelectorPlaceholder}>
                <img src="/icons/location_icon.svg" alt="Ubicación" />
                <span>{children}</span>
            </components.Placeholder>
        );
    }

    const pickerStyles = {
        control: (def) => ({
            ...def,
            height: '40px',
            backgroundColor: '#FFFFFF',
            border: 'none',
            borderRadius: '5px',
            display: 'flex',
            alignItems: 'center',
        }),
    };

    return (
        <div className={styles.citySelector}>
            <Select
                styles={pickerStyles}
                placeholder="¿A dónde vamos?"
                options={options}
                onChange={selectCity}
                value={selectedCity}
                components={{ SingleValue: SingleValue, Option: Option, DropdownIndicator: () => null, Placeholder: Placeholder, IndicatorSeparator: () => null }} />
        </div>
    );
}

export default CitySelector;