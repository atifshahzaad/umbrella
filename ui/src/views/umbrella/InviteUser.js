import React, { useState, useEffect } from 'react'
import PropTypes from 'prop-types';
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
    CFormCheck,
    CRow,
    CCol,
    CListGroup,
    CListGroupItem
} from '@coreui/react'

import * as roleService from '../../services/roleService'
import * as userService from '../../services/userService'


const InviteUser = ({ visible, closeInviteUserModal }) => {

    const [email, setEmail] = useState('')
    const [searchKey, setSearchKey] = useState('')
    const [supervisor, setSupervisor] = useState(null)
    const [supervisors, setSupervisors] = useState([])
    const [roles, setRoles] = useState([])
    const [selectedRoles, setSelectedRoles] = useState([])

    useEffect(() => {
        loadData()

    }, []);

    const invite = async () => {
        const data = {
            email,
            supervisor,
            roles: selectedRoles
        }

        const status = await userService.invite(data);
        console.log(status)
    }

    const handleCheckboxChange = (id) => {
        if (!selectedRoles.includes(id)) selectedRoles.push(id)
        else {
            selectedRoles.splice(selectedRoles.indexOf(id), 1)
        }

        console.log(selectedRoles)
    }

    const loadData = async () => {
        const roles = await roleService.getAll()
        setRoles(roles)
    }

    const search = async (key) => {
        setSearchKey(key)
        if (key.length > 3) {
            const data = await userService.searchUser(key)
            setSupervisors(data)
        }
    }

    const onSupervisorSelected = (id, name) => {
        setSupervisor(id);
        setSupervisors([]);
        setSearchKey(name)
    }

    return (
        <>
            <CModal size="lg" alignment="center" visible={visible} onClose={closeInviteUserModal}>
                <CModalHeader>
                    <CModalTitle>Invite User Now</CModalTitle>
                </CModalHeader>
                <CModalBody>
                    <CForm>
                        <div className="mb-3">
                            <CFormLabel htmlFor="email">Email address</CFormLabel>
                            <CFormInput
                                type="email"
                                id="email"
                                placeholder="name@example.com"
                                value={email}
                                onChange={(e) => {
                                    setEmail(e.target.value)
                                }}
                            />
                        </div>
                        <div className="mb-3">
                            <CFormLabel htmlFor="email">Select Supervisor</CFormLabel>
                            <CFormInput
                                type="text"
                                id="supervisor"
                                placeholder="search by name"
                                value={searchKey}
                                onChange={(e) => {
                                    search(e.target.value)
                                }}
                            />
                            <CListGroup>
                                {supervisors.map((item, index) => (
                                    <CListGroupItem key={index} component="a" href="#" onClick={ () => onSupervisorSelected(item.id, item.name)}>
                                        {item.name} | {item.role}
                                    </CListGroupItem>))}


                            </CListGroup>
                        </div>
                        <div>
                            <CRow>
                                {roles.map((item, index) => (
                                    <CCol xs={4} key={index}>
                                        <div className="mb-3" key={index}>
                                            <CFormCheck id={item.id} label={item.name} value={item.id}
                                                onChange={() => handleCheckboxChange(item.id)} /></div>
                                    </CCol>

                                ))}
                            </CRow>
                        </div>
                    </CForm>
                </CModalBody>
                <CModalFooter>
                    <CButton color="secondary" onClick={closeInviteUserModal}>
                        Close
                    </CButton>
                    <CButton color="primary" onClick={invite}>Invite</CButton>
                </CModalFooter>
            </CModal>
        </>
    )
}

InviteUser.propTypes = {
    visible: PropTypes.bool.isRequired,
    closeInviteUserModal: PropTypes.func.isRequired
};

export default InviteUser
