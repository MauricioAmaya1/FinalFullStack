import Home from "../ui/home/Home";
import Login from "../ui/login/Login";
import Booking from "../ui/booking/Booking";
import Product from "../ui/product/Product";
import Register from "../ui/register/Register";
import Administration from "../ui/administration/Administration";
import ReservesList from "../ui/myReserves/ReservesList";


const routes = [
    {
        path: "/",
        Element: Home,
    },
    {
        path: "home",
        Element: Home,
    },
    {
        path: "home/administration",
        Element: Administration,
    },
    {
        path: "login",
        Element: Login,
    },
    {
        path: "register",
        Element: Register,
    },
    {
        path: "product/:id",
        Element: Product,
    },
    {
        path: "product/:id/booking",
        Element: Booking,
    },
    {
        path: "myReserves",
        Element: ReservesList,
    },
    {
        path: "product/:id/administration",
        Element: Administration,
    },
];

export default routes;