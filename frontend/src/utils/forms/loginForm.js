import * as yup from 'yup';

export function defaultValuesLoginForm() {

    const validation = yup.object({
        email: yup
            .string('Ingresa tu email')
            .email('Ingresa un email valido')
            .required('El email es requerido'),
        password: yup
            .string('Ingresa tu contraseña')
            .required('La contraseña es requerida')
            .min(6, 'La contraseña debe tener un minimo de 6 caracteres')
    });

    const defaultValues = {
        email: '',
        password: '',
    }

    return { defaultValues, validation }
}