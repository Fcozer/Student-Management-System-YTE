import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from '../AppNavbar';
import { Link } from 'react-router-dom';

class AssistantList extends Component {

    constructor(props) {
        super(props);
        this.state = {assistants: []};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        fetch('/assistant')
            .then(response => response.json())
            .then(data => this.setState({assistants: data}));
    }

    async remove(id) {
        await fetch(`/assistant/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedAssistants = [...this.state.assistants].filter(i => i.id !== id);
            this.setState({assistants: updatedAssistants});
        });
    }

    render() {
        const {assistants} = this.state;

        const assistantList = assistants.map(assistant => {
            return <tr key={assistant.id}>
                <td style={{whiteSpace: 'nowrap'}}>{assistant.name}</td>
                <td>{assistant.surname}</td>
                <td>{assistant.username}</td>
                <td>{assistant.password}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="warning" tag={Link} to={"/assistant/" + assistant.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(assistant.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <br/>
                    <h3>Assistant Edit Page</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Name</th>
                            <th width="20%">Surname</th>
                            <th width="20%">Username</th>
                            <th width="20%">Password</th>
                            <th width="20%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {assistantList}
                        </tbody>
                    </Table>
                    <div className="float-right">
                        <Button color="dark" tag={Link} to="/assistant/new">Add Assistant</Button>
                    </div>
                </Container>
            </div>
        );
    }
}

export default AssistantList;