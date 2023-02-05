import React, { useState, useEffect } from 'react'
import PropTypes from 'prop-types';
import DatePicker from "react-datepicker";
import {
    CButton,
    CModal,
    CModalBody,
    CModalFooter,
    CModalHeader,
    CModalTitle,
    CForm,
    CFormInput,
    CFormLabel,
    CListGroup,
    CListGroupItem,
    CFormTextarea
} from '@coreui/react'

import * as userService from '../../../../services/userService'
import * as pmsService from '../../../../services/pmsService'

import "react-datepicker/dist/react-datepicker.css";

const NewProject = ({ visible, closeNewProjectModal }) => {

    const [name, setName] = useState('')
    const [description, setDescription] = useState('')
    const [manager, setManager] = useState('')
    const [startDate, setStartDate] = useState(null)
    const [searchKey, setSearchKey] = useState('')
    const [users, setUsers] = useState([])


    const search = async (key) => {
        setSearchKey(key)
        if (key.length > 3) {
            const data = await userService.searchUser(key)
            setUsers(data)
        }
    }

    const onManagerSelected = (id, name) => {
        setManager(id);
        setUsers([]);
        setSearchKey(name)
    }

    const create = async() => {

        const formattedDate = startDate.toLocaleDateString('en-GB', { year: 'numeric', month: '2-digit', day: '2-digit' })
        const body = {
            name,
            description,
            startDate: formattedDate,
            manager
        }

        try {
            const { status, data }  = await pmsService.createProject(body)
            console.log(status)
            console.log(data)
        } catch (error) {
            console.log(error)
        }

    }

    return (
        <>
            <CModal size="xl" alignment="center" visible={visible} onClose={closeNewProjectModal}>
                <CModalHeader>
                    <CModalTitle>Create New Project</CModalTitle>
                </CModalHeader>
                <CModalBody>
                    <CForm>
                        <div className="mb-3">
                            <CFormLabel htmlFor="email">Name</CFormLabel>
                            <CFormInput
                                type="name"
                                id="name"
                                value={name}
                                onChange={(e) => {
                                    setName(e.target.value)
                                }}
                            />
                        </div>
                        <div className="mb-3">
                            <CFormLabel htmlFor="email">Select Manager</CFormLabel>
                            <CFormInput
                                type="text"
                                id="manager"
                                placeholder="Search by name"
                                value={searchKey}
                                onChange={(e) => {
                                    search(e.target.value)
                                }}
                            />
                            <CListGroup>
                                {users.map((item, index) => (
                                    <CListGroupItem key={index} component="a" href="#" onClick={() => onManagerSelected(item.id, item.name)}>
                                        {item.name} | {item.role}
                                    </CListGroupItem>))}


                            </CListGroup>
                        </div>
                        <div>
                            <CFormLabel htmlFor="email">Select Start Date</CFormLabel>
                            <DatePicker selected={startDate} onChange={(date) => setStartDate(date)} />
                        </div>
                        <div>
                            <CFormLabel htmlFor="email">Description</CFormLabel>
                            <CFormTextarea id="description" rows="3" value={description}
                                onChange={(e) => {
                                    setDescription(e.target.value)
                                }}
                            ></CFormTextarea>
                        </div>
                    </CForm>
                </CModalBody>
                <CModalFooter>
                    <CButton color="secondary" onClick={closeNewProjectModal}>
                        Close
                    </CButton>
                    <CButton color="primary" onClick={create}>Create</CButton>
                </CModalFooter>
            </CModal>
        </>
    )

}

NewProject.propTypes = {
    visible: PropTypes.bool.isRequired,
    closeNewProjectModal: PropTypes.func.isRequired
};

export default NewProject;