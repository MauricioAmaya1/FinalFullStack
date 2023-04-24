import { GlobalContext } from '../../components/utils/global.context';

import styles from './reservesList.module.css'
import "./reserves.css"

import { useContext, useEffect, useState, Fragment } from 'react';
import { useNavigate } from 'react-router-dom';

import Swal from "sweetalert2";

import { urlBase } from '../../utils/apiUrl.json';
import { handleFetchGet } from '../../functions/handleFetch';

import LoadingScreen from '../template/loading/LoadingScreen'
import Hotel from '../../components/cards/hotel/Hotel'

const Reserves = () => {
    const { user: { userId } } = useContext(GlobalContext)

    const [data, setData] = useState([])
    const [loading, setLoading] = useState(true)

    const navigate = useNavigate()

    useEffect(() => {

        const emptyAlert = () => {
            Swal.fire({
                title: "RESERVAS VACIAS",
                text: "Realice una reserva para verla aqui",
                icon: 'warning',
                timer: '25000',
                confirmButtonColor: "#F0572D"
            }).then(() => {
                navigate("/home")
            })
        }

        handleFetchGet(`${urlBase}/reservas/user/${userId}`, setData, setLoading, emptyAlert, null, null)
    }, [])

    return (
        <div>
            {loading ? <LoadingScreen /> : (
                <Fragment>
                    <div className="container">
                        <div className="row">
                            <h1 className='centro'>Mis reservas</h1>
                            <div className={styles.reservesList}>
                                {data?.map((reserve, index) => <Hotel key={index} data={reserve.producto} isReserve={true} reserve={reserve} completeData={data} setCompleteData={setData} />)}
                            </div>
                        </div>
                    </div>
                </Fragment>
            )}
        </div>
    )
}

export default Reserves


