import { createContext, useState } from 'react'

export const ProductContext = createContext()

const ProductContextProvider = ({ children }) => {

    const [productData, setProductData] = useState(() => {
        if (sessionStorage.getItem("product")) {
            const product = sessionStorage.getItem("product")
            const parsedProduct = JSON.parse(product)

            return parsedProduct
        } else {
            return {}
        }
    })

    const [categories, setCategories] = useState(() => {
        if (sessionStorage.getItem("categories")) {
            const categories = sessionStorage.getItem("categories")
            const parsedCategories = JSON.parse(categories)

            return parsedCategories
        } else {
            return null
        }
    })

    const [cities, setCities] = useState(() => {
        if (sessionStorage.getItem("cities")) {
            const cities = sessionStorage.getItem("cities")
            const parsedCities = JSON.parse(cities)

            return parsedCities
        } else {
            return null
        }
    })

    const [atributes, setAtributes] = useState(() => {
        if (sessionStorage.getItem("atributes")) {
            const atributes = sessionStorage.getItem("atributes")
            const parsedAtributes = JSON.parse(atributes)

            return parsedAtributes
        } else {
            return null
        }
    })

    const store = {
        productData,
        categories,
        cities,
        atributes,
        setters: {
            setProductData,
            setCategories,
            setCities,
            setAtributes
        }
    }

    return (
        <ProductContext.Provider value={store}>
            {children}
        </ProductContext.Provider>
    )
}

export default ProductContextProvider