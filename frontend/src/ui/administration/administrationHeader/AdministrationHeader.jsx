import { useNavigate } from 'react-router-dom'
import styles from './administrationHeader.module.css'

const AdministrationHeader = () => {

  const navigate = useNavigate()

  return (
    <div className={styles.administrationHeader}>
        <div className='container'>
          <div className='row'>
            <div className={styles.titleContainer}>
              <h2 className={styles.title}>Administración</h2>
              <img onClick={() => navigate(-1)} className={styles.navigateBack} src="/backToHome.svg" alt="Navigate back" />
            </div>
          </div>
        </div>
      </div>
  )
}

export default AdministrationHeader