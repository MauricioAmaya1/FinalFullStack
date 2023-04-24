import { HomeContext } from '../../../../../components/utils/home.context';

import styles from './date_selector.module.css';

import "react-datepicker/dist/react-datepicker.css";

import { useContext } from 'react';

import DatePicker from 'react-datepicker';

const DateSelector = () => {

    const {
        startDate,
        endDate,

        setters: {
            setStartDate,
            setEndDate,
            setCompleteStartDate,
            setCompleteEndDate
        },
    } = useContext(HomeContext)

    const handleOnChange = (dates) => {
        const [start, end] = dates

        setStartDate(start)
        setEndDate(end)

        const startYear = start.getFullYear()
        const startMonth = start.getMonth() + 1
        const startDay = start.getDate()

        const startHours = start.getHours()
        const startHour = startHours < 10 ? '0' + startHours : startHours

        const startMinutes = start.getMinutes()
        const startMinute = (startMinutes < 10) ? '0' + startMinutes : startMinutes

        const startSeconds = start.getSeconds()
        const startSecond = (startSeconds < 10) ? '0' + startSeconds : startSeconds

        const startDate = `${startYear}-${startMonth}-${startDay}`
        const startTime = `${startHour}:${startMinute}:${startSecond}`

        const completeStartDate = `${startDate}-${startTime}`
        setCompleteStartDate(completeStartDate)

        if (end) {

            const endYear = end.getFullYear()
            const endMonth = end.getMonth() + 1
            const endDay = end.getDate()

            const endHours = end.getHours()
            const endHour = endHours < 10 ? '0' + endHours : endHours

            const endMinutes = end.getMinutes()
            const endMinute = (endMinutes < 10) ? '0' + endMinutes : endMinutes

            const endSeconds = end.getSeconds()
            const endSecond = (endSeconds < 10) ? '0' + endSeconds : endSeconds

            const endDate = `${endYear}-${endMonth}-${endDay}`
            const endTime = `${endHour}:${endMinute}:${endSecond}`

            const completeEndDate = `${endDate}-${endTime}`
            setCompleteEndDate(completeEndDate)

        }

    }

    return (
        <div className={styles.dateSelector}>
            <img src="/icons/calendar_icon.svg" alt="Calendario" />
            <DatePicker
                className={styles.datePicker}
                onChange={handleOnChange}
                startDate={startDate}
                endDate={endDate}
                minDate={new Date()}
                selectsRange
                shouldCloseOnSelect={false}
                strictParsing
                placeholderText='Check in - Check out'
                dateFormat='yyyy-MM-dd'
            />
        </div>
    );
}

export default DateSelector;