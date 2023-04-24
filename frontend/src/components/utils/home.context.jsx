import { createContext, useState } from 'react'

export const HomeContext = createContext()

const HomeContextProvider = ({ children }) => {

    const [recomendationsData, setRecomendationsData] = useState([])
    const [isLoading, setIsLoading] = useState(true)
    const [selectedCity, setSelectedCity] = useState(null)

    const [startDate, setStartDate] = useState(null)
    const [endDate, setEndDate] = useState(null);
    const [completeStartDate, setCompleteStartDate] = useState(null)
    const [completeEndDate, setCompleteEndDate] = useState(null)

    const store = {
        recomendationsData,
        isLoading,
        selectedCity,
        startDate,
        endDate,
        completeStartDate,
        completeEndDate,
        setters: {
            setRecomendationsData,
            setIsLoading,
            setSelectedCity,
            setStartDate,
            setEndDate,
            setCompleteStartDate,
            setCompleteEndDate
        }
    }

    return (
        <HomeContext.Provider value={store}>
            {children}
        </HomeContext.Provider>
    )
}

export default HomeContextProvider