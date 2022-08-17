
import React, { Component } from 'react';
import AppNavbar from '../AppNavbar';
import {Button, Container, NavLink} from 'reactstrap';
import {blue} from "@mui/material/colors";

class Admin extends Component {
    componentDidMount() {
        fetch('/admin')
            .then(response => response.json())
            .then(data => this.setState({admin: data}));
    }
    render() {
        return (


            <div class="admin">


                <AppNavbar/>
                <Container fluid>
                    <br/>
                    <h2>Hello Admin! How is your day going?</h2>
                    <br/>
                    <br/>
                    <NavLink href="/students">Student Edit Page </NavLink>
                    <br/>
                    <NavLink href="/academicians">Academicians Edit Page</NavLink>
                    <br/>
                    <NavLink href="/assistant">Assistant Edit Page</NavLink>
                    <br/>
                    <NavLink href="/Lessons">Lesson Edit Page</NavLink>


                </Container>
            </div>
        );
    }
}

export default Admin;