import { GlobalContext } from "../../components/utils/global.context";
import { ProductContext } from "../../components/utils/product.context";

import "./booking.css";
import styles from './booking.module.css'

import { useState, useContext } from "react";
import { useParams } from "react-router-dom";

import { Calendar } from "react-multi-date-picker";
import Swal from "sweetalert2";

import timesInput from "../../utils/times.json"
import { urlBase } from '../../utils/apiUrl.json'
import { handleFetchPost } from "../../functions/handleFetch";

import Dropdown from "./dropdown/Dropdown";
import HotelRating from "../../components/hotelRating/HotelRating";
import ProductHeader from "../product/product_header/ProductHeader";
import RulesAndConvenience from "../product/product_rulesAndConvenience/RulesAndConvenience";

const Booking = () => {
  const { user } = useContext(GlobalContext)
  const { productData } = useContext(ProductContext)

  const [value, setValue] = useState([]);

  const weekDays = ["D", "L", "M", "M", "J", "V", "S"];
  const months = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre",];

  const { id } = useParams();

  function handleChange(value) {
    setValue(value);
  }

  function handleClick() {
    const reserva = {
      fechaInicio: (`${value[0].year}-${value[0].month.number}-${value[0].day}`),
      fechaFinal: (`${value[1].year}-${value[1].month.number}-${value[1].day}`),
      producto: {
        id: id
      },
      user: {
        id: JSON.stringify(user.userId)
      }
    }

    if (value[0] && value[1]) {

      const headers = {
        'Content-type': 'application/json',
        'Autorizathion': `Bearer ${user.userToken}`
      }

      const succesMessage = () => {
        Swal.fire({
          title: "Exito",
          text: "Reservada realizada",
          icon: 'success',
          timer: '2500',
          confirmButtonColor: "#F0572D"
        })
      }

      const errorMessage = () => {
        Swal.fire({
          icon: 'error',
          title: 'Algo ha fallado...',
          text: 'Intentelo mas tarde',
        })
      }

      handleFetchPost(`${urlBase}/reservas`, reserva, headers, true, succesMessage, null, errorMessage, null)
    }
  }

  return (
    <>
      <div className="containerBooking">
        <ProductHeader data={productData} />
        <div className="container">
          <div className="row">
            <div className={styles.containerBookingFormDetails}>
              <div className={styles.completeYourInfo}>
                <div className={styles.containerBookingForm}>
                  <h2 style={{ marginBottom: "14px" }}>Completá tus datos</h2>
                  <form action="" className={styles.bookingForm}>
                    <div className={styles.containerBookingInput}>
                      <label style={{ fontWeight: "bold" }} htmlFor="">
                        Nombre
                      </label>
                      <input placeholder={user.userFirstName} className={styles.bookingInput} type="text" disabled />
                    </div>
                    <div className={styles.containerBookingInput}>
                      <label style={{ fontWeight: "bold" }} htmlFor="">
                        Apellido
                      </label>
                      <input placeholder={user.userLastName} className={styles.bookingInput} type="text" disabled />
                    </div>
                    <div className={styles.containerBookingInput}>
                      <label style={{ fontWeight: "bold" }} htmlFor="">
                        Email
                      </label>
                      <input placeholder={user.userEmail} className={styles.bookingInput} type="email" disabled />
                    </div>
                  </form>
                </div>
                <div className={styles.containerBookingCalendar}>
                  <h2 style={{ marginBottom: "14px" }}>
                    Seleccioná tu fecha de reserva
                  </h2>
                  <div className={styles.bookingCalendarPcContainer}>
                    <div className="bookingCalendar">
                      <Calendar
                        value={value}
                        onChange={handleChange}
                        weekDays={weekDays}
                        months={months}
                        numberOfMonths={2}
                        range
                        disableMonthPicker
                        disableYearPicker
                        minDate={new Date()}
                      />
                    </div>
                  </div>
                  <div className={styles.bookingCalendarMobileContainer}>
                    <div className="bookingCalendarMobile">
                      <Calendar
                        zIndex={1}
                        value={value}
                        onChange={handleChange}
                        weekDays={weekDays}
                        months={months}
                        numberOfMonths={1}
                        range
                        disableMonthPicker
                        disableYearPicker
                        minDate={new Date()}
                      />
                    </div>
                  </div>
                </div>
                <div className={styles.containerBookingTime}>
                  <h2 style={{ marginBottom: "14px" }}>Tu horario de llegada</h2>
                  <div className={styles.bookingTime}>
                    <h4
                      style={{
                        marginBottom: "14px",
                        display: "flex",
                        alignItems: "center",
                      }}
                    >
                      <img className={styles.checkIcon} src="/icons/check_icon.svg" alt="" />
                      Tu habitacion va a estar lista para el check-in entre las
                      10:00 AM y 11:00 PM
                    </h4>
                    <p style={{ color: "black", marginBottom: "10px", fontWeight: 'bold' }}>
                      Indica tu horario estimado de llegada
                    </p>
                    <Dropdown data={timesInput} value={'booking'} />
                  </div>
                </div>
              </div>
              <div className={styles.containerBookingDetails}>
                <h2 style={{ textAlign: 'center', margin: '20px' }}>Detalle de la reserva</h2>
                <img className={styles.imgBookingDetails} src={productData?.images[0].url} alt="" />
                <div className={styles.bookingDetails}>
                  <p style={{ color: 'gray', fontSize: '14px', fontWeight: 'bold', marginLeft: '0.6px' }}>{productData?.category.name}</p>
                  <h2 style={{ marginTop: '-6px' }}>{productData?.name}</h2>
                  <HotelRating rating={productData?.stars} />
                  <p style={{ color: 'black', display: 'flex', marginTop: '20px', fontWeight: 'bold', fontSize: '14px' }}>
                    <svg fill="#000000" width="21px" height="19px" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><g id="SVGRepo_bgCarrier" strokeWidth="0"></g><g id="SVGRepo_tracerCarrier" strokeLinecap="round" strokeLinejoin="round"></g><g id="SVGRepo_iconCarrier"><path d="M12,2a8,8,0,0,0-7.992,8A12.816,12.816,0,0,0,12,22v0H12v0a12.816,12.816,0,0,0,7.988-12A8,8,0,0,0,12,2Zm0,11a3,3,0,1,1,3-3A3,3,0,0,1,12,13Z"></path></g></svg>
                    {productData?.city.name}, {productData?.city.country}
                  </p>
                  <hr style={{ marginTop: '40px' }} />
                  <div className={styles.bookingDetailsCheck}>
                    <p style={{ color: 'black' }}>Check in</p>
                    <p style={{ color: 'red' }}>{value[0] ? (`${value[0].day}/${value[0].month.number}/${value[0].year}`) : 'Seleccione la fecha'}</p>
                  </div>
                  <hr style={{ marginTop: '40px' }} />
                  <div className={styles.bookingDetailsCheck}>
                    <p style={{ color: 'black' }}>Check out</p>
                    <p style={{ color: 'red' }}>{value[1] ? (`${value[1].day}/${value[1].month.number}/${value[1].year}`) : 'Seleccione la fecha'}</p>
                  </div>
                  <hr style={{ marginTop: '40px', marginBottom: '40px' }} />
                  <button onClick={handleClick} className={styles.btnBooking}>Confirmar reserva</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <RulesAndConvenience dataHouseRules={productData.houseRules} dataHealthAndSecurity={productData.healthAndSecurity} dataCancelPolitics={productData.cancelPolitics} />
      </div>
    </>
  );
};

export default Booking;
