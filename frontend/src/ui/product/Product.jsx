import { GlobalContext } from '../../components/utils/global.context';
import { ProductContext } from '../../components/utils/product.context';

import { Fragment, useContext, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

import { urlBase } from '../../utils/apiUrl.json'
import { handleFetchGet } from '../../functions/handleFetch';

import LoadingScreen from '../template/loading/LoadingScreen';
import ProductHeader from './product_header/ProductHeader';
import ProductImages from './product_images/ProductImages';
import ProductDescription from './product_description/ProductDescription';
import ProductServices from './product_services/ProductServices';
import ProductReservation from './product_reservation/ProductReservation';
import ProductMap from './product_map/ProductMap';
import RulesAndConvenience from './product_rulesAndConvenience/RulesAndConvenience';


const Product = () => {

    const { isLogged } = useContext(GlobalContext)

    const {
        setters: {
            setProductData
        }
    } = useContext(ProductContext)

    const { id } = useParams();

    const [data, setData] = useState({})
    const [loading, setLoading] = useState(true)

    const uploadToSessionStorage = (response) => {
        if (isLogged) {

            const atributesId = []
            response.caracteristicas.map((atribute) => atributesId.push(atribute.id))

            const product = {
                name: response.titulo,
                category: {
                    id: response.categoria.id,
                    name: response.categoria.titulo
                },
                location: {
                    latitude: response.latitud,
                    longitude: response.longitud,
                    altitude: response.altura,
                    centerDistance: response.distanciaCentro
                },
                city: {
                    id: response.ciudad.id,
                    name: response.ciudad.nombre_ciudad,
                    country: response.ciudad.nombre_pais
                },
                centerDistance: response.distanciaCentro,
                description: {
                    title: response.tituloDescripcion,
                    text: response.descripcion
                },
                atributes: atributesId,
                stars: response.estrellas,
                points: response.puntuacion,
                quality: response.review,
                houseRules: response.politicaLugar,
                healthAndSecurity: response.politicaSaludSeguridad,
                cancelPolitics: response.politicaCancelacion,
                images: response.listImagen
            }

            setProductData(product)
            sessionStorage.setItem("product", JSON.stringify(product))
        }
    }

    useEffect(() => {
        handleFetchGet(`${urlBase}/productos/${id}`, setData, setLoading, null, null, uploadToSessionStorage)
    }, [])

    return (
        <div>
            {loading ? <LoadingScreen /> : (
                <Fragment>
                    <ProductHeader data={data} />
                    <ProductImages images={data.listImagen.map((image) => image.url)} />
                    <ProductDescription dataDescription={data.descripcion} dataTitle={data.tituloDescripcion} />
                    <ProductServices data={data} />
                    <ProductReservation data={data} />
                    <ProductMap data={data} />
                    <RulesAndConvenience dataHouseRules={data.politicaLugar} dataHealthAndSecurity={data.politicaSaludSeguridad} dataCancelPolitics={data.politicaCancelacion} />
                </Fragment>
            )}
        </div>
    )
}

export default Product;