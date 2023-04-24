import { ProductContext } from "../../components/utils/product.context";
import { useContext } from "react";
import * as yup from 'yup';
import { extractNumbersFromString } from '../../functions/extractNumbersFromString'

let isOnHome

function checkIfLocationHome() {
    if (location.pathname === "/home/administration") {
        isOnHome = true
    } else {
        isOnHome = false
    }
}

export function defaultValuesAdminForm() {

    const validation = yup.object({
        name: yup
            .string('Ingresa un nombre valido')
            .required('El nombre es requerido'),
        category: yup
            .string('Ingresa una categoria valida')
            .required('La categoria es requerida'),
        latitude: yup
            .number()
            .typeError('Ingresa una latitud valida')
            .required('La latitud es requerida'),
        longitude: yup
            .number()
            .typeError('Ingresa una longitud valida')
            .required('La longitud es requerida'),
        altitude: yup
            .number()
            .typeError('Ingresa una altitud valida')
            .required('La altitud es requerida'),
        centerDistance: yup
            .number()
            .typeError('Ingresa una distancia valida')
            .required('La altitud es requerida'),
        city: yup
            .string('Ingresa una ciudad valida')
            .required('La ciudad es requerida'),
        descriptionTitle: yup
            .string('Ingresa un titulo valido')
            .required('El titulo es requerido'),
        description: yup
            .string('Ingresa una descripcion valida')
            .required('La descripcion es requerida')
            .min(30, "La descripcion debe tener un minimo de 30 caracteres"),
        houseRules: yup
            .string('Ingresa unas reglas validas')
            .required('Las reglas son requeridas')
            .min(10, "Las politicas deben tener un minimo de 10 caracteres"),
        healthAndSecurity: yup
            .string('Ingresa una descripcion valida')
            .required('La descripcion es requerida')
            .min(10, "Las politicas deben tener un minimo de 10 caracteres"),
        cancelPolitics: yup
            .string('Ingresa unas politicas validas')
            .required('Las politicas son requeridas')
            .min(10, "Las politicas deben tener un minimo de 10 caracteres"),
    })

    const { productData } = useContext(ProductContext)
    checkIfLocationHome()

    const defaultValues = {
        name: isOnHome ? "" : productData.name,
        category: isOnHome ? "" : productData.category.id,
        latitude: isOnHome ? "" : productData.location.latitude,
        longitude: isOnHome ? "" : productData.location.longitude,
        altitude: isOnHome ? "" : productData.location.altitude,
        centerDistance: isOnHome ? "" : extractNumbersFromString(productData.location.centerDistance),
        city: isOnHome ? "" : productData.city.id,
        descriptionTitle: isOnHome ? "" : productData.description.title,
        description: isOnHome ? "" : productData.description.text,
        atributes: "",
        houseRules: isOnHome ? "" : productData.houseRules,
        healthAndSecurity: isOnHome ? "" : productData.healthAndSecurity,
        cancelPolitics: isOnHome ? "" : productData.cancelPolitics,
        imageTitle: "",
        images: "",
    }

    return { defaultValues, validation }
}
