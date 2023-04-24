import Categories from "./categories/Categories"
import Recomendations from "./recomendations/Recomendations";
import Search from "./search/Search"

const Home = () => {

    return (
        <div className='home'>
            <Search />
            <Categories />
            <Recomendations />
        </div>
    )
}

export default Home;