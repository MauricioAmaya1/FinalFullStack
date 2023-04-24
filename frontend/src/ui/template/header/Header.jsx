import { GlobalContext } from '../../../components/utils/global.context';
import { HomeContext } from '../../../components/utils/home.context';

import styles from "./header.module.css"

import { Fragment, useContext, useState } from 'react'
import { useLocation, useNavigate, useParams } from 'react-router-dom';

import { urlBase } from "../../../utils/apiUrl.json"
import { admin } from "../../../utils/rolesId.json"
import { handleFetchGet } from "../../../functions/handleFetch"

import UserMenu from '../userMenu/UserMenu';

const Header = () => {

    const {
        user,
        isLogged,

        setters: {
            setUser,
            setIsLogged
        }
    } = useContext(GlobalContext)

    const [isAdmin] = useState(() => {
        if (user.userRoles?.indexOf(admin) != -1) {
            return true
        } else {
            return false
        }
    })

    const { setters: recomendationsSetters } = useContext(HomeContext)

    const [showUserMenu, setShowUserMenu] = useState(false)

    const location = useLocation()
    const { id } = useParams()
    const navigate = useNavigate();

    const closeSession = () => {
        sessionStorage.clear()
        setUser({})
        setIsLogged(false)
    }

    const adminRedirect = () => {
        if (location.pathname === `/product/${id}` || location.pathname === `/product/${id}/booking` || location.pathname === `/product/${id}/administration`) {
            navigate(`/product/${id}/administration`)
        } else {
            navigate("/home/administration")
        }
    }

    const goToHome = () => {

        if (location.pathname === "/" || location.pathname === "/home") {

            recomendationsSetters.setSelectedCity(null)
            recomendationsSetters.setStartDate(null)
            recomendationsSetters.setEndDate(null)
            recomendationsSetters.setCompleteStartDate(null)
            recomendationsSetters.setCompleteEndDate(null)

            handleFetchGet(`${urlBase}/productos/random`, recomendationsSetters.setRecomendationsData, recomendationsSetters.setIsLoading, null, null)

        } else {
            navigate("/home")
        }

    }

    const handleOpenUserMenu = () => {
        setShowUserMenu(true)
        document.body.style.overflow = "hidden"
    }

    return (
        <div className={styles.header}>
            <div className='container'>
                <div className='row'>
                    <div className={styles.headerContent}>
                        <button className={styles.logoContainer} onClick={() => goToHome()}>
                            <img src="/logo.svg" alt="Logo" />
                            <h2 className={styles.title}>Sentite como en tu hogar</h2>
                        </button>
                        <img onClick={handleOpenUserMenu} className={styles.menuCel} src="/menuCel.svg" alt="Menu cel" />
                        <div className={styles.btnContainer}>
                            {isLogged ?
                                <Fragment>
                                    {isAdmin && <span className={styles.administration} onClick={adminRedirect}>Administración</span>}
                                    <div className={styles.userContainer} style={isAdmin ? { borderLeft: "solid #F0572D 3px", paddingLeft: "10px" } : {}}>
                                        <button className={`${styles.userIcon} ${styles.btn}`} onClick={() => navigate("/myReserves")}>{`${user.userFirstName.charAt(0).toUpperCase()}${user.userLastName.charAt(0).toUpperCase()}`}</button>
                                        <div className={styles.welcomeContainer}>
                                            <p>Hola,</p>
                                            <p className={styles.userName}>{user.userFirstName} {user.userLastName}</p>
                                        </div>
                                    </div>
                                </Fragment>
                                :
                                <button onClick={() => navigate("/register")} style={location.pathname === "/register" ? { display: 'none' } : { display: 'block' }} className={styles.btn}>Crear cuenta</button>
                            }

                            {isLogged ?
                                <img onClick={closeSession} className={styles.closeSessionIcon} src="/icons/closeSession_icon.svg" alt="Close session icon" />
                                :
                                <button onClick={() => navigate("/login")} style={location.pathname === "/login" ? { display: 'none' } : { display: 'block' }} className={styles.btn}>Iniciar sesión</button>}
                        </div>
                    </div>
                </div>
            </div>
            {showUserMenu && <UserMenu show={showUserMenu} setShowUserMenu={setShowUserMenu} />}
        </div>
    )
}

export default Header