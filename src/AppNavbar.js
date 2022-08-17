import React, {Component} from 'react';
import {Button, Nav, Navbar, NavbarBrand, NavItem, NavLink} from 'reactstrap';
import {Link} from 'react-router-dom';

export default class AppNavbar extends Component {
    constructor(props) {
        super(props);
        this.state = {isOpen: false};
        this.toggle = this.toggle.bind(this);
    }

    toggle() {
        this.setState({
            isOpen: !this.state.isOpen
        });
    }


    render() {
        return <Navbar color="dark" dark expand="md">
            <NavbarBrand tag={Link} to="/admin" className="me-auto"><strong>ADMIN</strong></NavbarBrand>

            <Nav   navbar>
                <NavItem>
                    <NavLink href="/students">Students</NavLink>
                </NavItem>
                <NavItem>
                    <NavLink href="/academicians">Academicians</NavLink>
                </NavItem>
                <NavItem>
                    <NavLink href="/assistant">Assistants</NavLink>
                </NavItem>
                <NavItem>
                    <NavLink href="/Lessons">Lesson</NavLink>
                </NavItem>
                <NavItem>
                    <NavLink href="/">Logout</NavLink>
                </NavItem>
            </Nav>

        </Navbar>;
    }
}