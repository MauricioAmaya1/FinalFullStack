import * as yup from 'yup';

export function defaultValuesRegisterForm() {

    const validation = yup.object({
        name: yup
            .string('Ingresa tu nombre')
            .required('Su nombre es requerido'),
        surname: yup
            .string('Ingresa tu apellido')
            .required('Tu apellido es requerido'),
        email: yup
            .string('Ingresa tu email')
            .email('Ingresa un email valido')
            .required('El email es requerido'),
        password: yup
            .string('Ingresa tu contraseña')
            .min(6, 'La contraseña debe tener un minimo de 6 caracteres')
            .required('La contraseña es requerida'),
        confirmPassword: yup
            .string('Ingresa tu contraseña')
            .min(6, 'La contraseña debe tener un minimo de 6 caracteres')
            .required('La contraseña es requerida')
            .oneOf([yup.ref('password'), null], 'Las contraseñas no coinciden')
    });

    const defaultValues = {
        name: '',
        surname: '',
        email: '',
        password: '',
        confirmPassword: ''
    }

    return { defaultValues, validation }
}