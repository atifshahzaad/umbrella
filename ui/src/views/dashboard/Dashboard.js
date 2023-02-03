import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import {
    CButton,
    CCard,
    CCardImage,
    CCardTitle,
    CCardText,
    CCardBody,
    CCardHeader,
    CCol,
    CRow,
    CFormFloating,
    CFormInput,
    CFormLabel,
    CCardFooter,
    CAlert
} from '@coreui/react'
import InviteUser from '../umbrella/InviteUser'
import Role from '../umbrella/Role'
import * as userService from '../../services/userService'


import ReactImg from 'src/assets/images/react.jpg'

const Dashboard = () => {
    const navigate = useNavigate();
    const [isLoading, setIsLoading] = useState(true);
    const [showInviteUserModal, setShowInviteUserModal] = useState(false)
    const [showRoleModal, setShowRoleModal] = useState(false)
    const [user, setUser] = useState(null)
    const [clonedUser, setClonedUser] = useState(null)
    const [updatedUser, setUpdatedUser] = useState({})
    const [updatBtn, setUpdatBtn] = useState(true)
    useEffect(() => {
        getUserDetail()

    }, []);

    const getUserDetail = async () => {
        try {
            const { status, data } = await userService.getMyDetail();
            console.log(status)
            if (status === 401) {

                setIsLoading(false)
                navigate("/login")
            }
            else {
                setUser(data)
                setClonedUser(data)
            }
        } catch (error) {
            console.log(error);
        }

        setIsLoading(false)

    }


    const closeInviteUserModal = () => {
        setShowInviteUserModal(false)
    }

    const closeRoleModal = () => {
        setShowRoleModal(false)
    }

    const infoUpdated = (value, key) => {
        const updatedValue = value === "" ? null : value;
        if (updatedValue !== clonedUser[key]) {
            updatedUser[key] = updatedValue;
        } else {
            delete updatedUser[key];
        }
        setUpdatBtn(Object.keys(updatedUser).length === 0);
    }

    const cancelUpdate = () => {
        setUser(clonedUser)
        setUpdatedUser({})
        setUpdatBtn(Object.keys(updatedUser).length === 0);
    }


    const update = async () => {
        try {
            const status = await userService.update(user.id, updatedUser);
            console.log(status)
        } catch (error) {
            console.log(error)
        }

    }

    if (isLoading) {
        return <p>Loading...</p>
    }

    return (
        <>

            <CRow>
                <CCol xs={12}>
                    <CCard className="mb-4">
                        <CCardHeader>
                            <strong>HR Panel</strong>
                        </CCardHeader>
                        <CCardBody>
                            <CRow>
                                <CCol xs={4}>
                                    <CCard style={{ width: '18rem' }}>
                                        <CCardImage orientation="top" src={ReactImg} />
                                        <CCardBody>
                                            <CCardTitle>Welcome {user.firstName} {user.middleName} {user.lastName}</CCardTitle>
                                            <CCardText>
                                                Your Email: {user.email}
                                            </CCardText>
                                            <CButton href="#">Go somewhere</CButton>
                                        </CCardBody>
                                    </CCard>
                                </CCol>
                                <CCol xs={8}>
                                    <div href="components/modal#vertically-centered">
                                        {showInviteUserModal && <InviteUser visible={showInviteUserModal} closeInviteUserModal={closeInviteUserModal} />}
                                        {showRoleModal && <Role visible={showRoleModal} closRoleModal={closeRoleModal} />}
                                        <CButton onClick={() => setShowInviteUserModal(true)} className='hr-pnl-btn'>Invite User</CButton>
                                        <CButton onClick={() => setShowRoleModal(true)} className='hr-pnl-btn'>System Roles</CButton>
                                        {/* <CButton onClick={() => setShowInviteUser(true)} className='hr-pnl-btn'>Update Supervisor</CButton>
                                        <CButton onClick={() => setShowInviteUser(true)} className='hr-pnl-btn'>Update Post</CButton>
                                        <CButton onClick={() => setShowInviteUser(true)} className='hr-pnl-btn'>Farewell Employee</CButton>
                                        <CButton onClick={() => setShowInviteUser(true)} className='hr-pnl-btn'>Create Event</CButton>
                                        <CButton onClick={() => setShowInviteUser(true)} className='hr-pnl-btn'>Add Job</CButton>
                                        <CButton onClick={() => setShowInviteUser(true)} className='hr-pnl-btn'>Search Employee</CButton>
                                        <CButton onClick={() => setShowInviteUser(true)} className='hr-pnl-btn'>Message</CButton> */}
                                    </div>

                                </CCol>
                            </CRow>


                        </CCardBody>
                    </CCard>
                    <CCard className="mb-4">
                        <CCardHeader>
                            <strong>My Info</strong>
                        </CCardHeader>
                        <CCardBody>
                            <CRow>
                                <CCol xs={6} className="info-col">
                                    <CFormFloating>
                                        <CFormInput
                                            type="email"
                                            defaultValue={user.email}
                                        />
                                        <CFormLabel>Email</CFormLabel>
                                    </CFormFloating>
                                </CCol>
                                <CCol xs={6} className="info-col">
                                    <CFormFloating>
                                        <CFormInput
                                            type="text"
                                            defaultValue={user.firstName}
                                            onChange={(e) => {
                                                infoUpdated(e.target.value, 'firstName')
                                            }}
                                        />
                                        <CFormLabel>First Name</CFormLabel>
                                    </CFormFloating>
                                </CCol>
                                <CCol xs={6} className="info-col">
                                    <CFormFloating>
                                        <CFormInput
                                            type="text"
                                            defaultValue={user.middleName}
                                            onChange={(e) => {
                                                infoUpdated(e.target.value, 'middleName')
                                            }}
                                        />
                                        <CFormLabel>Middle Name</CFormLabel>
                                    </CFormFloating>
                                </CCol>
                                <CCol xs={6} className="info-col">
                                    <CFormFloating>
                                        <CFormInput
                                            type="text"
                                            defaultValue={user.lastName}
                                            onChange={(e) => {
                                                infoUpdated(e.target.value, 'lastName')
                                            }}
                                        />
                                        <CFormLabel>Last Name</CFormLabel>
                                    </CFormFloating>
                                </CCol>
                            </CRow>
                        </CCardBody>
                        <CCardFooter>
                            <CButton className="float-end hr-pnl-btn" active={updatBtn} onClick={cancelUpdate}>Cancel</CButton>
                            <CButton className="float-end hr-pnl-btn" active={updatBtn} onClick={update}>Save</CButton>
                        </CCardFooter>
                    </CCard>
                </CCol>
            </CRow>

        </>
    )
}

export default Dashboard
