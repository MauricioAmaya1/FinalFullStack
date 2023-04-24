import styles from './administrationForm.module.css'

import { useEffect, useState } from 'react';
import { useLocation, useNavigate, useParams } from 'react-router-dom'
import { useForm } from 'react-hook-form';

import { yupResolver } from '@hookform/resolvers/yup';
import ReactImageGallery from 'react-image-gallery';
import Swal from 'sweetalert2';

import { defaultValuesAdminForm } from '../../../utils/forms/adminForm';
import { urlBase } from '../../../utils/apiUrl.json'
import { handleFetchDelete, handleFetchPost, handleFetchPut } from '../../../functions/handleFetch';

const AdministrationForm = ({ data, categories, cities, atributes, user }) => {

    const location = useLocation()

    const { id } = useParams()

    const navigate = useNavigate()

    const { defaultValues, validation } = defaultValuesAdminForm()

    const [imageAlert, setImageAlert] = useState(null)
    const [imageTitleAlert, setImageTitleAlert] = useState(null)
    const [isOnHome, setIsOnHome] = useState(null)
    const [actualImageIndex, setActualImageIndex] = useState(0)

    const [totalAtributes, setTotalAtributes] = useState(() => {
        if (location.pathname != "/home/administration") {
            const completedAtributes = data.atributes.map((id) => atributes.find((atr) => atr.id == id))
            return completedAtributes
        } else {
            return []
        }
    })

    const [totalImages, setTotalImages] = useState(() => {

        if (data.images) {

            const readyForGallery = data.images?.map((image) => (
                {
                    originalHeight: "500px",
                    originalWidth: "500px",
                    original: image.url,
                    thumbnailHeight: "50px",
                    thumbnailWidth: "50px",
                    thumbnail: image.url,
                    thumbnailLabel: image.titulo,
                }
            ))
            return readyForGallery

        } else {
            return []
        }

    })

    useEffect(() => {
        if (location.pathname === "/home/administration") {
            setIsOnHome(true)
            setTotalImages([])
        } else {
            setIsOnHome(false)
        }
    }, [])

    const { register, handleSubmit, getValues, reset, formState: { errors } } = useForm({

        resolver: yupResolver(validation),
        defaultValues: defaultValues
    })

    const getImageIndex = (index) => {
        setActualImageIndex(index)
    }

    const handleRemoveImage = () => {

        if (totalImages.length === 1) {

            setTotalImages([])

        } else {

            const images = [...totalImages]
            images.splice(actualImageIndex, 1)
            setTotalImages(images)

        }

    }

    const handleAddNewImage = () => {
        const urlImageToAdd = getValues("images")
        const titleImageToAdd = getValues("imageTitle")

        let inputElement = document.createElement('input')
        inputElement.type = 'url'
        inputElement.value = urlImageToAdd

        let validation = true

        if (urlImageToAdd.length >= 1) {
            if (!inputElement.checkValidity()) {
                validation = false
                setImageAlert(true)
            } else {
                setImageAlert(false)
            }

        } else {
            validation = false
            setImageAlert(true)
        }

        if (!titleImageToAdd.length >= 1) {
            validation = false
            setImageTitleAlert(true)
        } else {
            setImageTitleAlert(false)
        }

        if (validation) {

            const image = {
                originalHeight: "500px",
                originalWidth: "500px",
                original: urlImageToAdd,
                thumbnailHeight: "50px",
                thumbnailWidth: "50px",
                thumbnail: urlImageToAdd,
                thumbnailLabel: titleImageToAdd,
            }

            reset({
                imageTitle: "",
                images: ""
            })
            setTotalImages([...totalImages, image])
        }
    }

    const handleAddNewAtribute = () => {
        const selectedAtributeId = getValues("atributes")

        if (selectedAtributeId.length >= 1) {
            const alreadyExist = totalAtributes.some(atribute => {
                if (atribute.id == selectedAtributeId) {
                    return true
                }
                return false
            })

            if (alreadyExist) {
                const removeAtribute = totalAtributes.filter(({ id }) => !selectedAtributeId.includes(id))
                setTotalAtributes(removeAtribute)

            } else {
                const selectedAtribute = atributes.find(atribute => atribute.id == selectedAtributeId)
                setTotalAtributes([...totalAtributes, selectedAtribute])
            }
        }

    }

    const onDelete = () => {
        Swal.fire({
            title: 'Estas seguro?',
            text: "No se podra deshacer",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Eliminar'
        }).then((result) => {
            if (result.isConfirmed) {

                const headers = {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${user.userToken}`
                }

                const succesMessage = () => {
                    Swal.fire(
                        'Eliminado!',
                        'El producto se ha eliminado con exito',
                        'success'
                    ).then(() => {
                        navigate("/home")
                    })
                }

                const errorMessage = () => {
                    Swal.fire({
                        icon: 'error',
                        title: 'Algo ha fallado...',
                        text: 'Intentelo mas tarde',
                    })
                }

                handleFetchDelete(`${urlBase}/productos/${id}`, headers, true, succesMessage, errorMessage)
            }
        })
    }

    const onSubmit = data => {

        const atributesToPost = totalAtributes.map((atribute) => (
            {
                id: atribute.id,
                nombre: atribute.nombre,
                icono: atribute.icono
            }
        ))

        const images = totalImages.map((image) => (
            {
                titulo: image.thumbnailLabel,
                url: image.original
            }
        ))

        const calculateStars = () => {
            let stars = 0
            const sumatorie = 5 / atributes.length
            atributesToPost.forEach(() => {
                stars = stars + sumatorie
            })
            return Math.round(stars)
        }

        const calculateReview = () => {
            const points = calculateStars() * 2

            if (points > 7) {
                return "Excelente"
            } else if (points > 4) {
                return "Muy bueno"
            } else {
                return "Bueno"
            }
        }

        const product = {
            titulo: data.name,
            categoria: {
                id: data.category
            },
            ciudad: {
                id: data.city
            },
            caracteristicas: atributesToPost,
            listImagen: images,
            review: calculateReview(),
            puntuacion: calculateStars() * 2,
            estrellas: calculateStars(),
            tituloDescripcion: data.descriptionTitle,
            descripcion: data.description,
            politicaLugar: data.houseRules,
            politicaSaludSeguridad: data.healthAndSecurity,
            politicaCancelacion: data.cancelPolitics,
            latitud: data.latitude,
            longitud: data.longitude,
            altura: data.altitude,
            distanciaCentro: `A ${data.centerDistance}m del centro`
        }

        let validation = true

        if (totalAtributes.length < 1) {
            validation = false
        }

        if (totalImages.length < 5) {
            validation = false

        }

        if (validation) {

            const headers = {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${user.userToken}`
            }

            const succesMessage = () => {
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: 'El producto se registro con exito',
                    showConfirmButton: false,
                    timer: 1500
                }).then(() => {
                    if (isOnHome) {
                        reset({
                            name: "",
                            category: "",
                            latitude: "",
                            longitude: "",
                            altitude: "",
                            centerDistance: "",
                            city: "",
                            descriptionTitle: "",
                            description: "",
                            atributes: "",
                            houseRules: "",
                            healthAndSecurity: "",
                            cancelPolitics: "",
                            imageTitle: "",
                            images: "",
                        })
                        setTotalAtributes([])
                        setTotalImages([])
                    }
                })
            }

            const errorMessage = () => {
                Swal.fire({
                    icon: 'error',
                    title: 'Algo ha fallado...',
                    text: 'Intentelo mas tarde',
                })
            }

            if (isOnHome) {
                handleFetchPost(`${urlBase}/productos`, product, headers, true, succesMessage, null, errorMessage, null, null)
            } else {
                handleFetchPut(`${urlBase}/productos`, { ...product, id: id }, headers, true, succesMessage, errorMessage)
            }

        } else {
            Swal.fire({
                icon: 'error',
                title: 'Algo ha fallado...',
                text: 'El producto debe tener un minimo de 5 imagenes y 1 atributo',
            })
        }
    }

    return (
        <div className={styles.administrationForm}>
            <div className='container'>
                <div className='row'>
                    <div className={styles.formContainer}>
                        <h2 className={styles.title}>{location.pathname === "/home/administration" ? "Crear propiedad" : "Editar propiedad"}</h2>
                        <form className={styles.form} onSubmit={handleSubmit(onSubmit)}>
                            <div className={styles.nameCategory}>

                                <div className={styles.inputContainer}>
                                    <label className={styles.labelForm} htmlFor="name">Nombre de la propiedad</label>
                                    <input className={styles.input} style={errors.name && { border: "solid 1px red" }}
                                        id="name"
                                        name="name"
                                        {...register("name")}>
                                    </input>
                                    {errors.name && <span className={styles.errorAlert}>{errors.name.message}</span>}
                                </div>

                                <div className={styles.inputContainer}>
                                    <label className={styles.labelForm} htmlFor="category">Categoria</label>
                                    <select className={`${styles.input} ${styles.select}`} style={errors.category && { border: "solid 1px red" }}
                                        id="category"
                                        name="category"
                                        {...register("category")}>
                                        {categories?.map((category, index) => <option className={styles.inputOption} key={index} value={category.id}>{category.name}</option>)}
                                    </select>
                                    {errors.category && <span className={styles.errorAlert}>{errors.category.message}</span>}
                                </div>

                            </div>

                            <div className={styles.locationCity}>
                                <div className={styles.locationContainer}>
                                    <h3 className={styles.locationTitle}>Localización</h3>
                                    <div className={styles.locationInputsContainer}>

                                        <div className={`${styles.inputContainer} ${styles.containerLocation} ${styles.containerLatitude}`}>
                                            <label className={`${styles.labelForm} ${styles.labelLocation}`} htmlFor="latitude">Latitud</label>
                                            <input className={`${styles.input} ${styles.inputLocation}`} style={errors.latitude && { border: "solid 1px red" }}
                                                id="latitude"
                                                name="latitude"
                                                {...register("latitude")}>
                                            </input>
                                            {errors.latitude && <span className={styles.errorAlert}>{errors.latitude.message}</span>}
                                        </div>

                                        <div className={`${styles.inputContainer} ${styles.containerLocation} ${styles.containerLongitude}`}>
                                            <label className={`${styles.labelForm} ${styles.labelLocation}`} htmlFor="longitude">Longitud</label>
                                            <input className={`${styles.input} ${styles.inputLocation}`} style={errors.longitude && { border: "solid 1px red" }}
                                                id="longitude"
                                                name="longitude"
                                                {...register("longitude")}>
                                            </input>
                                            {errors.longitude && <span className={styles.errorAlert}>{errors.longitude.message}</span>}
                                        </div>

                                        <div className={`${styles.inputContainer} ${styles.containerLocation} ${styles.containerAltitude}`}>
                                            <label className={`${styles.labelForm} ${styles.labelLocation}`} htmlFor="altitude">Altitud</label>
                                            <input className={`${styles.input} ${styles.inputLocation}`} style={errors.altitude && { border: "solid 1px red" }}
                                                id="altitude"
                                                name="altitude"
                                                {...register("altitude")}>
                                            </input>
                                            {errors.altitude && <span className={styles.errorAlert}>{errors.altitude.message}</span>}
                                        </div>

                                        <div className={`${styles.inputContainer} ${styles.containerLocation} ${styles.containerCenterDistance}`}>
                                            <label className={`${styles.labelForm} ${styles.labelLocation}`} htmlFor="centerDistance">Distancia del centro (m)</label>
                                            <input className={`${styles.input} ${styles.inputLocation}`} style={errors.centerDistance && { border: "solid 1px red" }}
                                                id="centerDistance"
                                                name="centerDistance"
                                                {...register("centerDistance")}>
                                            </input>
                                            {errors.centerDistance && <span className={styles.errorAlert}>{errors.centerDistance.message}</span>}
                                        </div>

                                    </div>
                                </div>

                                <div className={`${styles.inputContainer} ${styles.containerCity}`}>
                                    <label className={styles.labelForm} htmlFor="city">Ciudad</label>
                                    <select className={`${styles.input} ${styles.select}`} style={errors.city && { border: "solid 1px red" }}
                                        id="city"
                                        name="city"
                                        {...register("city")}>
                                        {cities?.map((city, index) => <option className={styles.inputOption} key={index} value={city.id}>{city.name}</option>)}
                                    </select>
                                    {errors.city && <span className={styles.errorAlert}>{errors.city.message}</span>}
                                </div>

                            </div>

                            <div className={styles.descriptionContainer}>
                                <h3 className={styles.descriptionTitle}>Descripcion</h3>
                                <div className={styles.descriptionInputsContainer}>
                                    <div className={`${styles.inputContainer} ${styles.containerDescriptionTitle}`}>
                                        <label className={`${styles.labelForm}`} htmlFor="descriptionTitle">Titulo</label>
                                        <input className={`${styles.input} ${styles.inputLocation}`} style={errors.descriptionTitle && { border: "solid 1px red" }}
                                            id="descriptionTitle"
                                            name="descriptionTitle"
                                            {...register("descriptionTitle")}>
                                        </input>
                                        {errors.descriptionTitle && <span className={styles.errorAlert}>{errors.descriptionTitle.message}</span>}
                                    </div>

                                    <div className={`${styles.inputContainer} ${styles.containerLarge}`}>
                                        <label className={styles.labelForm} htmlFor="description">Texto (Agregar punto(.) entre parrafo)</label>
                                        <textarea className={`${styles.input} ${styles.inputDescription}`} style={errors.description && { border: "solid 1px red" }}
                                            id="description"
                                            name="description"
                                            {...register("description")}>
                                        </textarea>
                                        {errors.description && <span className={styles.errorAlert}>{errors.description.message}</span>}
                                    </div>
                                </div>
                            </div>

                            <div className={styles.atributesContainer}>
                                <h3 className={styles.atributesTitle}>Agregar atributos</h3>
                                <div className={styles.atributesInputsContainer}>

                                    <div className={`${styles.inputContainer} ${styles.containerAtributes}`}>
                                        <label className={styles.labelForm} htmlFor="atributes">Atributos</label>
                                        <select className={`${styles.input} ${styles.select}`} style={errors.atributes && { border: "solid 1px red" }}
                                            id="atributes"
                                            name="atributes"
                                            {...register("atributes")}>
                                            <option value="" disabled hidden>Elije los atributos a añadir</option>
                                            {atributes?.map((atribute, index) => <option className={`${styles.inputOption} ${styles.atributeName}`} key={index} value={atribute.id}>{atribute.nombre}</option>)}
                                        </select>
                                        <button type='button' onClick={handleAddNewAtribute} className={styles.addButton}>
                                            Añadir
                                        </button>
                                    </div>

                                    <div className={styles.atributesImgContainer}>
                                        {totalAtributes?.map((atribute, index) => (
                                            <div className={styles.atributeImgContainer} key={index}>
                                                <img className={styles.atributeImg} src={atribute?.icono} alt={atribute?.nombre}></img>
                                                <span className={styles.atributeName}>{atribute?.nombre}</span>
                                            </div>
                                        ))}
                                    </div>

                                </div>
                            </div>

                            <div className={styles.politicsContainer}>
                                <h3 className={styles.politicsTitle}>Políticas del producto</h3>
                                <h5>(Agregar punto(.) entre politica)</h5>
                                <div className={`${styles.politicsInputsContainer} ${styles.form}`}>

                                    <div className={styles.politicsSectionContainer}>
                                        <h4>Normas de la casa</h4>
                                        <label htmlFor="houseRules">Descripción</label>
                                        <textarea className={`${styles.input} ${styles.inputPolitics}`} style={errors.houseRules && { border: "solid 1px red" }}
                                            id="houseRules"
                                            name="houseRules"
                                            {...register("houseRules")}>
                                        </textarea>
                                        {errors.houseRules && <span className={styles.errorAlert}>{errors.houseRules.message}</span>}
                                    </div>

                                    <div className={styles.politicsSectionContainer}>
                                        <h4>Salud y seguridad</h4>
                                        <label htmlFor="healthAndSecurity">Descripción</label>
                                        <textarea className={`${styles.input} ${styles.inputPolitics}`} style={errors.healthAndSecurity && { border: "solid 1px red" }}
                                            id="healthAndSecurity"
                                            name="healthAndSecurity"
                                            {...register("healthAndSecurity")}>
                                        </textarea>
                                        {errors.healthAndSecurity && <span className={styles.errorAlert}>{errors.healthAndSecurity.message}</span>}
                                    </div>

                                    <div className={styles.politicsSectionContainer}>
                                        <h4>Política de cancelacion</h4>
                                        <label htmlFor="cancelPolitics">Descripción</label>
                                        <textarea className={`${styles.input} ${styles.inputPolitics}`} style={errors.cancelPolitics && { border: "solid 1px red" }}
                                            id="cancelPolitics"
                                            name="cancelPolitics"
                                            {...register("cancelPolitics")}>
                                        </textarea>
                                        {errors.cancelPolitics && <span className={styles.errorAlert}>{errors.cancelPolitics.message}</span>}
                                    </div>

                                </div>
                            </div>

                            <div className={styles.imagesContainer}>
                                <h3>Cargar imágenes</h3>
                                <div className={`${styles.imageInputContainer} ${styles.containerLarge}`}>

                                    <div className={`${styles.inputContainer} ${styles.containerImageTitle}`}>
                                        <label className={`${styles.labelForm}`} htmlFor="imageTitle">Titulo</label>
                                        <input className={`${styles.input} ${styles.inputImages}`} style={imageTitleAlert ? { border: "solid 1px red" } : {}}
                                            id="imageTitle"
                                            name="imageTitle"
                                            {...register("imageTitle")}>
                                        </input>
                                        {imageTitleAlert && <span className={styles.errorAlert}>Introduce un titulo valido</span>}
                                    </div>

                                    <div className={`${styles.inputContainer} ${styles.containerImage}`}>
                                        <label className={`${styles.labelForm}`} htmlFor="images">Url</label>
                                        <input className={`${styles.input} ${styles.inputImages}`} style={imageAlert ? { border: "solid 1px red" } : {}}
                                            id="images"
                                            name="images"
                                            {...register("images")}>
                                        </input>
                                        {imageAlert && <span className={styles.errorAlert}>Introduce una url valida</span>}
                                        <div className={styles.imageButtonsContainer}>
                                            <button type='button' onClick={handleAddNewImage} className={`${styles.addButton} ${styles.uploadImageButton}`}>
                                                Añadir
                                            </button>
                                            <button type='button' onClick={handleRemoveImage} className={`${styles.addButton} ${styles.uploadImageButton} ${styles.deleteButton}`}>
                                                Eliminar
                                            </button>
                                        </div>
                                    </div>

                                </div>

                                <div className={styles.imagesGallery}>
                                    {totalImages.length >= 1 && <ReactImageGallery onSlide={getImageIndex} items={totalImages} showPlayButton={false} useBrowserFullscreen={false} showFullscreenButton={false} thumbnailPosition='top' />}
                                </div>
                            </div>

                            <div className={styles.submitButtonsContainer}>
                                <button className={`${styles.submitButton} ${styles.addButton}`}>{location.pathname === "/home/administration" ? "Crear" : "Editar"}</button>
                                {location.pathname != "/home/administration" && <button type='button' onClick={onDelete} className={`${styles.submitButton} ${styles.addButton} ${styles.deleteButton}`}>Eliminar</button>}
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AdministrationForm