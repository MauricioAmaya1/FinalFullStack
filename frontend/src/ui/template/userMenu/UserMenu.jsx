import { GlobalContext } from '../../../components/utils/global.context'

import styles from "./userMenu.module.css"

import { useContext, useEffect, useState } from 'react'
import { useNavigate, useLocation, useParams } from 'react-router-dom'

import { admin } from '../../../utils/rolesId.json'

const UserMenu = ({ setShowUserMenu }) => {

    const {
        user,
        isLogged,

        setters: {
            setUser,
            setIsLogged
        }
    } = useContext(GlobalContext)

    const [isAdmin] = useState(() => {
        if(user.userRoles?.indexOf(admin) != -1) {
            return true
        } else {
            return false
        }
    })

    const navigate = useNavigate()

    const { id } = useParams()
    const location = useLocation()

    const unLoggedOptions = ["Crear cuenta", "Iniciar sesión"]
    const unLoggedUrls = ["/register", "/login"]
    const adminOptions = ["Administración"]
    const adminUrls = []
    const [totalOptions, setTotalOptions] = useState([])
    const [totalUrls, setTotalUrls] = useState([])

    const closeSession = () => {
        sessionStorage.clear()
        setUser({})
        setIsLogged(false)
    }

    const adminAssignUrl = () => {
        if (location.pathname === `/product/${id}` || location.pathname === `/product/${id}/booking` || location.pathname === `/product/${id}/administration`) {
            adminUrls.unshift(`/product/${id}/administration`)
        } else {
            adminUrls.unshift("/home/administration")
        }
    }

    useEffect(() => {
        if (isLogged) {
            if (isAdmin) {
                adminAssignUrl()
                setTotalOptions(...[adminOptions])
                setTotalUrls(...[adminUrls])
            }
        } else {
            setTotalOptions(...[unLoggedOptions])
            setTotalUrls(...[unLoggedUrls])
        }
    }, [isLogged])

    const handleCloseUserMenu = () => {
        setShowUserMenu(false)
        document.body.style.overflow = "auto"
    }

    return (
        <div className={styles.userMenu}>
            <div className={styles.headerMenu}>
                <img onClick={handleCloseUserMenu} className={styles.closeMenu} src="/icons/close_menu_icon.svg" alt="" />
                {isLogged ? (
                    <div className={styles.userContainer}>
                        <button className={`${styles.userIcon} ${styles.btn}`} onClick={() => navigate("/myReserves")}>{`${user.userFirstName.charAt(0).toUpperCase()}${user.userLastName.charAt(0).toUpperCase()}`}</button>
                        <div className={styles.welcomeContainer}>
                            <p className={styles.welcome}>Hola,</p>
                            <p className={styles.userName}>{user.userFirstName} {user.userLastName}</p>
                        </div>
                    </div>
                ) : <h2 className={styles.headerTitle}>Menú</h2>}
            </div>
            <div className={styles.contentContainer}>
                <ul className={styles.list}>
                    {totalOptions.map((option, id) => <li className={styles.listOptions} onClick={() => navigate(totalUrls[id])} key={id}>{option}</li>)}
                </ul>
            </div>
            <div className={styles.menuFooter}>
                {isLogged && <p className={styles.closeSessionContainer}>¿Deseas<span onClick={closeSession} className={styles.closeSession}> cerrar sesion</span>?</p>}
                <ul className={styles.socialMedia}>
                    <li className={styles.socialMediaListItem} onClick={() => window.location.href = "https://www.facebook.com"} ><img className={styles.socialMediaIcon} src="/icons/facebook_filled_icon.svg" alt="Facebook Icon" /></li>
                    <li className={styles.socialMediaListItem} onClick={() => window.location.href = "https://www.linkedin.com"} ><img className={styles.socialMediaIcon} src="/icons/linkedin_filled_icon.svg" alt="Linkedin Icon" /></li>
                    <li className={styles.socialMediaListItem} onClick={() => window.location.href = "https://www.twitter.com"} ><img className={styles.socialMediaIcon} src="/icons/twitter_filled_icon.svg" alt="Twitter Icon" /></li>
                    <li className={styles.socialMediaListItem} onClick={() => window.location.href = "https://www.instagram.com"} ><img className={styles.socialMediaIcon} src="/icons/instagram_filled_icon.svg" alt="Instagram Icon" /></li>
                </ul>
            </div>
        </div>
    )
}

export default UserMenu