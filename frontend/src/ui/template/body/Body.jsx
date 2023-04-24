import React from 'react'
import { Outlet } from 'react-router-dom';

import "./body.module.css"

const Body = () => {
  return (
    <div className='body'>
      <Outlet />        
    </div>
  )
}

export default Body