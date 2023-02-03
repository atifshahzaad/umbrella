import React, { useState, useEffect } from 'react'
import PropTypes from 'prop-types';
import {
    CButton,
    CModal,
    CModalBody,
    CModalFooter,
    CModalHeader,
    CModalTitle,
    CRow,
    CCol,
    CTable,
    CTableBody,
    CTableDataCell,
    CTableHead,
    CTableHeaderCell,
    CTableRow,
} from '@coreui/react'

import * as roleService from '../../services/roleService'


const Role = ({ visible, closRoleModal }) => {

    const [roles, setRoles] = useState([])

    useEffect(() => {
        loadData()

    }, []);

    const deleteRole = async(id) => {
        await roleService.deleteRole(id)
        await loadData();

    }

    const loadData = async () => {
        const roles = await roleService.getAll()
        setRoles(roles)
    }

    return (
        <>
            <CModal size="lg" alignment="center" visible={visible} onClose={closRoleModal}>
                <CModalHeader>
                    <CModalTitle>Invite User Now</CModalTitle>
                </CModalHeader>
                <CModalBody>
                    <CRow>
                        <CCol xs={12}>
                            <CTable small align="middle" responsive>
                                <CTableHead>
                                    <CTableRow>
                                        <CTableHeaderCell scope="col">#</CTableHeaderCell>
                                        <CTableHeaderCell scope="col">Name</CTableHeaderCell>
                                        <CTableHeaderCell scope="col">Action</CTableHeaderCell>
                                    </CTableRow>
                                </CTableHead>
                                <CTableBody>
                                    {roles.map((item, index) => (
                                        <CTableRow key={item.id}>
                                            <CTableHeaderCell scope="row">{index}</CTableHeaderCell>
                                            <CTableDataCell>{item.name}</CTableDataCell>
                                            <CTableDataCell>
                                            <CButton color="danger" onClick={() => deleteRole(item.id)}>Delete</CButton>
                                            </CTableDataCell>
                                        </CTableRow>
                                    ))}
                                </CTableBody>
                            </CTable>
                        </CCol>
                    </CRow>
                </CModalBody>
                <CModalFooter>
                    <CButton color="secondary" onClick={() => closRoleModal()}>
                        Close
                    </CButton>
                </CModalFooter>
            </CModal>
        </>
    )
}

Role.propTypes = {
    visible: PropTypes.bool.isRequired,
    closRoleModal: PropTypes.func.isRequired
};

export default Role
