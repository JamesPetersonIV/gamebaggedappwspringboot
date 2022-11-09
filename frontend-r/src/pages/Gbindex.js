import React from "react"
//import image directly
import GameBaggedLogo from '../images/GBLogo.png';
import brendenhs from '../images/brenden_image1.jpg';
import jameshs from '../images/JamesHS.jpg';
import kamarihs from '../images/kamari_Image.jpg';
import nehemiehs from '../images/IMG_3683.jpg';


const gbindex = () => {
    return(
        <div style={{backgroundColor: '#a1acb4'}}>

            <div>
                <nav className="one_a_level">
                    <a href="">
                        <img src={GameBaggedLogo} alt={'Logo'}/>
                    </a>
                    <a href="">
                        &#9776;
                    </a>
                </nav>
            </div>

            <div className="one_b_level">
                <table>
                    <tr>
                        <td>
                            <p>
                                Tired of waiting in line for the new/gadgets in stores?
                            </p>
                            <p>
                                Wait no more!
                            </p>
                        </td>

                        <td>
                            <img src={GameBaggedLogo} alt="logo here"
                                 height="176px" width="204px"/>

                            <p>
                                Got your back!
                            </p>
                        </td>
                    </tr>
                </table>
            </div>

            <div className="one_c_level">
                <p>
                    .......
                </p>
                <table>
                    <tr>
                        <td>
                            <a href={'/signin'}> <img src="" alt="dollar sign"
                                                      height="50" width="50"/>
                            </a>
                            <p>
                                Bagger login
                            </p>
                        </td>

                        <td>
                            <b>
                                Or
                            </b>
                        </td>

                        <td>
                            <a href={'/signin'}> <img src="" height="50" width="50"/>
                            </a>
                            <p>
                                Customer login
                            </p>
                        </td>
                    </tr>
                </table>

                <h1> Creators </h1>

                <table>
                    <tr>
                        <td>
                            <img src={jameshs} height="125" width="125"/>
                            <p>
                                James Peterson IV
                            </p>
                        </td>
                        <td>
                            <img src={kamarihs} height="125"
                                 width="125"/>
                            <p>
                                Kamari J. Williams
                            </p>
                        </td>
                        <td>
                            <img src={brendenhs} height="125"
                                 width="125"/>
                            <p>
                                Brenden Driver
                            </p>
                        </td>
                        <td>
                            <img src={nehemiehs} height="125" width="125"/>
                            <p>
                                Nehemie Augustin
                            </p>
                        </td>
                    </tr>
                </table>
            </div>

            <footer>
                <p>
                    &copy; Copyright 2022. All Rights Reserved
                </p>

                <p>
                    <a href="mailto:"> </a>
                </p>
            </footer>

        </div>

    );
}

export default gbindex