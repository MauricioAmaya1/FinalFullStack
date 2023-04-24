import styles from './productServices.module.css'

const ProductServices = ({ data }) => {

    return (
        <div className={styles.productServices}>
            <div className={styles.title}>
                <div className='container'>
                    <div className='row'>
                        <h2 className={styles.content}>¿Qué ofrece este lugar?</h2>
                    </div>
                </div>
            </div>
            <div className='container'>
                <div className='row'>
                    <ul className={styles.serviceList}>
                        {data.caracteristicas.map((service, id) => <li key={id} className={styles.service}><img className={styles.serviceIcon} src={service.icono} alt="Service icon" /><p>{service.nombre?.charAt(0).toUpperCase() + service.nombre?.slice(1)}</p></li>)}
                    </ul>
                </div>
            </div>
        </div>
    )
}

export default ProductServices