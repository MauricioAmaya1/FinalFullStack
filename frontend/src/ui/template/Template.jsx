import { Outlet } from "react-router-dom";

import Header from './header/Header';
import './body/Body';
import './footer/Footer';
import Body from "./body/Body";
import Footer from "./footer/Footer";

function Template() {

    return (
        <>
            <Header />
            <Body />
            <Footer />
        </>
    );
}

export default Template;