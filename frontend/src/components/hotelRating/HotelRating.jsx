import styles from './hotel_rating.module.css';

const HotelRating = ({ rating }) => {

    const buildStars = (rating) => {
        const stars = [];

        for (let i = 0; i < 5; i++) {
            if (i < rating) {
                stars.push(<img key={i} src="/icons/star_icon_filled.svg" />);
            } else {
                stars.push(<img key={i} src="/icons/star_icon.svg" />);
            }
        }

        return stars;
    }

    return (
        <div className={styles.hotelRating}>
            { buildStars(rating) }
        </div>
    )

}

export default HotelRating;