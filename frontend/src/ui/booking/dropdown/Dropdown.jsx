import "./dropdown.css";

import { useState } from "react";

const Dropdown = ({ data, value }) => {

  const [time, setTime] = useState(null)

  const [open, setOpen] = useState(false);

  function handleClick(e) {
    setOpen(!open);
  }

  let text2 = time ? `${time.hour}` : "Seleccioná la hora de llegada";

  return (
    <div className="dropdown">
      <div className="dropdown-btn" onClick={handleClick}>
        {value == "city" && (
          <div style={{ display: "flex", alignItems: "center" }}>
            <svg
              fill="#000000"
              width="24px"
              height="24px"
              viewBox="0 0 24 24"
              xmlns="http://www.w3.org/2000/svg"
            >
              <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
              <g
                id="SVGRepo_tracerCarrier"
                strokeLinecap="round"
                strokeLinejoin="round"
              ></g>
              <g id="SVGRepo_iconCarrier">
                <path d="M12,2a8,8,0,0,0-7.992,8A12.816,12.816,0,0,0,12,22v0H12v0a12.816,12.816,0,0,0,7.988-12A8,8,0,0,0,12,2Zm0,11a3,3,0,1,1,3-3A3,3,0,0,1,12,13Z"></path>
              </g>
            </svg>
          </div>
        )}
        {value == "booking" && <div>{text2}</div>}
        <svg
          style={{ paddingLeft: "10px" }}
          width="34px"
          height="34px"
          viewBox="0 0 25 25"
          fill="none"
          xmlns="http://www.w3.org/2000/svg"
        >
          <g id="SVGRepo_bgCarrier" strokeWidth="0"></g>
          <g
            id="SVGRepo_tracerCarrier"
            strokeLinecap="round"
            strokeLinejoin="round"
          ></g>
          <g id="SVGRepo_iconCarrier">
            {" "}
            <path
              d="M17 10.5L12.5 15L8 10.5"
              stroke="#121923"
              strokeWidth="1.2"
            ></path>{" "}
          </g>
        </svg>
      </div>
      {open && value == "booking" && (
        <div className="otherDropdown-content">
          {data?.map((data) => (
            <div
              className="dropdown-item"
              onClick={() => {
                setTime(data);
                setOpen(false);
              }}
              key={data.id}
            >
              {data.hour}
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Dropdown;
