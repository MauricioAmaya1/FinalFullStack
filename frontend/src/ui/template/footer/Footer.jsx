import React from 'react'
import styles from './footer.module.css'

const Footer = () => {
    return (
        <div className={styles.footer}>
            <div className='container'>
                <div className='row'>
                    <div className={styles.footerContent}>
                        <h3 className={styles.copyright}>©2023 Digital Booking</h3>
                        <ul className={styles.SocialMedia}>
                            <li><img className={styles.socialMediaIcon} onClick={() => window.location.href = "https://www.facebook.com"} src="/icons/facebook_icon.svg" alt="Facebook Icon" /></li>
                            <li><img className={styles.socialMediaIcon} onClick={() => window.location.href = "https://www.linkedin.com"} src="/icons/linkedin_icon.svg" alt="Linkedin Icon" /></li>
                            <li><img className={styles.socialMediaIcon} onClick={() => window.location.href = "https://www.twitter.com"} src="/icons/twitter_icon.svg" alt="Twitter Icon" /></li>
                            <li><img className={styles.socialMediaIcon} onClick={() => window.location.href = "https://www.instagram.com"} src="/icons/instagram_icon.svg" alt="Instagram Icon" /></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Footer