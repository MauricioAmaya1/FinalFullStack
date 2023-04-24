import { GlobalContext } from '../../components/utils/global.context';

import styles from './login.module.css'

import { useContext, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import { useForm } from 'react-hook-form';

import { yupResolver } from '@hookform/resolvers/yup';
import Swal from 'sweetalert2';

import { urlBase } from '../../utils/apiUrl.json'
import { defaultValuesLoginForm } from '../../utils/forms/loginForm';
import { handleFetchPost } from '../../functions/handleFetch';

const Login = () => {
    const {
        setters: {
            setIsLogged,
            setUser
        }
    } = useContext(GlobalContext)

    const navigate = useNavigate();
    const location = useLocation()

    const [requireLogin] = useState(() => {
        if (location.state === "requireLogin") {
            return true
        } else {
            return false
        }
    })

    const { defaultValues, validation } = defaultValuesLoginForm()

    const { register, handleSubmit, formState: { errors } } = useForm({

        resolver: yupResolver(validation),
        defaultValues: defaultValues

    })

    const onSubmit = data => {

        const user = {
            email: data.email,
            password: data.password
        }

        const headers = {
            'Content-Type': 'application/json'
        }

        const errorMessage = () => {
            Swal.fire({
                title: "Error",
                text: "Contraseña o email incorrectos",
                icon: 'error',
                timer: '2500',
                confirmButtonColor: "#F0572D"
            })
        }

        const uploadToSessionStorage = (response) => {

            const userRoles = []

            response.roles.map((role) => userRoles.push(role.id))

            const user = {
                userId: response.id,
                userFirstName: response.first_name,
                userLastName: response.last_name,
                userEmail: response.email,
                userToken: response.token,
                userRoles: userRoles

            }

            sessionStorage.setItem("user", JSON.stringify(user))
            setUser(user)
            setIsLogged(true)
        }

        const succes = () => {
            navigate("/home", { replace: true })
        }

        handleFetchPost(`${urlBase}/api/v1/auth/authenticate`, user, headers, null, null, errorMessage, errorMessage, succes, uploadToSessionStorage)
    }

    return (
        <div className={styles.login}>
            {requireLogin && <p className={styles.requireLogin}>Necesita iniciar sesion para esta funcion</p>}
            <h2 className={styles.title}>Iniciar sesion</h2>
            <form onSubmit={handleSubmit(onSubmit)} className={styles.formLogin} action="">
                <div className={styles.inputContainer}>
                    <label className={styles.labelForm} htmlFor="email">Correo electrónico</label>
                    <input className={styles.input} style={errors.email && { border: "solid 1px red" }}
                        id='email'
                        name='email'
                        type="email"
                        autoComplete='off'
                        {...register("email")} />
                    {errors.email && <span className={styles.formError}>{errors.email.message}</span>}
                </div>

                <div className={styles.inputContainer}>
                    <label className={styles.labelForm} htmlFor="password">Contraseña</label>
                    <input className={styles.input} style={errors.password && { border: "solid 1px red" }}
                        id='password'
                        name='password'
                        type="password"
                        autoComplete='off'
                        {...register("password")} />
                    {errors.password && <span className={styles.formError}>{errors.password.message}</span>}
                </div>
                <div className={styles.submitContainer}>
                    <button type='submit' className={styles.btn}>Ingresar</button>
                    <div>
                        <span>¿Aun no tenes cuenta?</span>
                        <button className={styles.navigationLink} onClick={() => navigate("/register")}>Registrarse</button>
                    </div>
                </div>
            </form>
        </div>
    )
}

export default Login