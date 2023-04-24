import styles from './productDescription.module.css'

import { textToArray } from '../../../functions/textToArray'

const ProductDescription = ({ dataDescription, dataTitle }) => {

  const description = textToArray(dataDescription)

  return (
    <div className={styles.productDescription}>
      <div className='container'>
        <div className='row'>
          <h2 className={`${styles.title} ${styles.content}`}>{`${dataTitle}`}</h2>
          {description?.map((paragraph, id) => <p key={id} className={`${styles.descriptionParagraph} ${styles.content}`}>{paragraph}</p>)}
        </div>
      </div>
    </div>
  )
}

export default ProductDescription