import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from '../AppNavbar';

class LessonEdit extends Component {

    emptyItem = {
        lessonName: '',
        startTimeSlot: '',
        endTimeSlot: '',
        academicianId:''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const lesson = await (await fetch(`/Lessons/${this.props.match.params.id}`)).json();
            this.setState({item: lesson});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;

        await fetch('/Lessons' + (item.id ? '/' + item.id : ''), {
            method: (item.id) ? 'PUT' : 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/Lessons');
    }

    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit lesson' : 'Add Lesson'}</h2>;

        return <div>
            <AppNavbar/>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="lessonName">Lesson Name</Label>
                        <Input type="text" name="lessonName" id="lessonName" value={item.lessonName || ''}
                               onChange={this.handleChange} autoComplete="lessonName"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="startTimeSlot">Lesson Start Time</Label>
                        <Input type="time" name="startTimeSlot" id="startTimeSlot" value={item.startTimeSlot || ''}
                               onChange={this.handleChange} autoComplete="startTimeSlot"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="endTimeSlot">Lesson End Time</Label>
                        <Input type="time" name="endTimeSlot" id="endTimeSlot" value={item.endTimeSlot || ''}
                               onChange={this.handleChange} autoComplete="endTimeSlot"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="academicianId">academicianId</Label>
                        <Input type="text" name="academicianId" id="academicianId" value={item.academicianId || ''}
                               onChange={this.handleChange} autoComplete="academicianId"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/Lessons">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
}

export default withRouter(LessonEdit);