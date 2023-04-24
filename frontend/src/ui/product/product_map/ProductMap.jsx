import styles from './productMap.module.css'


const ProductMap = ({ data }) => {

  const coordinates1 = data.altura
  const coordinates2 = data.longitud
  const coordinates3 = data.latitud

  return (
    <div className={styles.productMap}>
      <div className={styles.title}>
        <div className='container'>
          <div className='row'>
            <h2 className={styles.content}>¿Dónde vas a estar?</h2>
          </div>
        </div>
      </div>
      <div className='container'>
        <div className='row'>
          <h3 className={`${styles.subtitle} ${styles.content}`}>{data.ciudad.nombre_ciudad}, {data.ciudad.nombre_pais}</h3>
          <iframe className={`${styles.map} ${styles.content}`} src={`https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d${coordinates1}!2d${coordinates2}!3d${coordinates3}!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1ses!2suy!4v1678229845225!5m2!1ses!2suy`} allowFullScreen="" loading="lazy" referrerPolicy='no-referrer-when-downgrade'></iframe>
        </div>
      </div>
    </div>
  )
}

export default ProductMap