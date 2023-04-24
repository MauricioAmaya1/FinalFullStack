import styles from "./hotels_list.module.css";

import Hotel from '../../../../components/cards/hotel/Hotel';

const HotelsList = ({ data }) => {

    return (
        <div className={styles.hotelsList}>
            {data?.map(hotel => <Hotel key={hotel.id} data={hotel} isReserve={false} />)}
        </div>
    );

}

export default HotelsList;