import React, { useState, useEffect } from 'react'

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
} from '@coreui/react'

import NewProject from '../new-project/NewProject'

import ReactImg from 'src/assets/images/react.jpg'

const PRMSHome = () => {
    const [showNewProjectModal, setNewProjectModal] = useState(false)



    const closeNewProjectModal = () => {
        setNewProjectModal(false)
    }

    return (
        <>

            <CRow>
                <CCol xs={12}>

                    {showNewProjectModal && <NewProject visible={showNewProjectModal} closeNewProjectModal={closeNewProjectModal} />}

                    <CCard className="mb-4">
                        <CCardHeader>
                            <strong>Projects</strong>
                            <div className='float-end'>
                                <CButton onClick={() => setNewProjectModal(true)}>New Project
                                </CButton></div>
                        </CCardHeader>
                        <CCardBody>
                            <CRow>
                                <CCol xs={3}>
                                    <CCard style={{ width: '18rem' }}>
                                        <CCardImage orientation="top" src={ReactImg} />
                                        <CCardBody>
                                            <CCardTitle>Card title</CCardTitle>
                                            <CCardText>
                                                Some quick example text to build on the card title and make up the bulk of the
                                                card&#39;s content.
                                            </CCardText>
                                            <CButton href="#">Create New</CButton>
                                        </CCardBody>
                                    </CCard>
                                </CCol>
                                <CCol xs={3}>
                                    <CCard style={{ width: '18rem' }}>
                                        <CCardImage orientation="top" src={ReactImg} />
                                        <CCardBody>
                                            <CCardTitle>Card title</CCardTitle>
                                            <CCardText>
                                                Some quick example text to build on the card title and make up the bulk of the
                                                card&#39;s content.
                                            </CCardText>
                                            <CButton href="#">Go somewhere</CButton>
                                        </CCardBody>
                                    </CCard>
                                </CCol>
                            </CRow>
                        </CCardBody>
                    </CCard>
                </CCol>
            </CRow>

        </>
    )
}


export default PRMSHome