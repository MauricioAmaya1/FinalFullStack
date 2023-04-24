import { GlobalContext } from '../../components/utils/global.context'
import { ProductContext } from '../../components/utils/product.context'

import { Fragment, useContext, useEffect, useState } from 'react'

import { urlBase } from '../../utils/apiUrl.json'
import { handleFetchGet } from '../../functions/handleFetch'

import AdministrationHeader from './administrationHeader/AdministrationHeader'
import AdministrationForm from './administrationForm/AdministrationForm'
import LoadingScreen from '../template/loading/LoadingScreen'


const Administration = () => {

  const { user } = useContext(GlobalContext)
  const {
    productData,
    categories,
    cities,
    atributes,

    setters: productSetters
  } = useContext(ProductContext)

  const [loading, setLoading] = useState(true)

  useEffect(() => {
    setLoading(true)

    if (!categories) {
      const uploadToSessionStorage = (response) => {

        const customCategories = response.map((categorie) => (
          {
            id: categorie.id,
            name: categorie.titulo
          }
        ))

        sessionStorage.setItem("categories", JSON.stringify(customCategories))
        productSetters.setCategories(customCategories)
      }
      handleFetchGet(`${urlBase}/categorias`, null, null, null, null, uploadToSessionStorage)
    }

    if (!cities) {
      const uploadToSessionStorage = (response) => {

        const customCities = response.map((city) => (
          {
            id: city.id,
            name: city.nombre_ciudad
          }
        ))

        sessionStorage.setItem("cities", JSON.stringify(customCities))
        productSetters.setCities(customCities)
      }
      handleFetchGet(`${urlBase}/ciudades`, null, null, null, null, uploadToSessionStorage)
    }

    if (!atributes) {
      const uploadToSessionStorage = (response) => {
        sessionStorage.setItem("atributes", JSON.stringify(response))
        productSetters.setAtributes(response)
      }
      handleFetchGet(`${urlBase}/caracteristicas`, null, setLoading, null, null, uploadToSessionStorage)
    }

    if (categories && cities && atributes) {
      setLoading(false)
    }

  }, [])

  return (
    <div className='administration'>
      {loading ? <LoadingScreen /> : (
        <Fragment>
          <AdministrationHeader />
          <AdministrationForm data={productData} categories={categories} cities={cities} atributes={atributes} user={user} />
        </Fragment>
      )}
    </div>
  )
}

export default Administration