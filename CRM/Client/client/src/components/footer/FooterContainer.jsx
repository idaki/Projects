import React, { useState, useEffect } from 'react';
import Modal from 'react-modal';
import styles from '../user-auth/user-auth-container/UserAuthContainerModal'; 
import modalStyles from '../footer/FooterContainer.module.css';
import LanguageSelectorModal from '../language/LanguageSelectorModal';
import { useTranslation } from 'react-i18next'; 

Modal.setAppElement('#root');

export default function Footer() {
    const { t } = useTranslation();
    const [showCookiePolicy, setShowCookiePolicy] = useState(false);
    const [showTermsOfService, setShowTermsOfService] = useState(false);
    const [cookiePolicyContent, setCookiePolicyContent] = useState('');
    const [termsOfServiceContent, setTermsOfServiceContent] = useState('');

    useEffect(() => {
        // Fetch the content of the cookie policy and terms of service files
        const fetchContent = async () => {
            try {
                const cookiePolicyResponse = await fetch('/assets/cookie_policy.txt'); // Updated path for cookie policy
                const cookiePolicyText = await cookiePolicyResponse.text();
                setCookiePolicyContent(cookiePolicyText);
    
                const termsOfServiceResponse = await fetch('/assets/terms_of_service.txt'); // Updated path for terms of service
                const termsOfServiceText = await termsOfServiceResponse.text();
                setTermsOfServiceContent(termsOfServiceText);
            } catch (error) {
                console.error('Error fetching content:', error);
            }
        };
    
        fetchContent();
    }, []);

    const toggleCookiePolicyModal = () => {
        setShowCookiePolicy(!showCookiePolicy);
    };

    const toggleTermsOfServiceModal = () => {
        setShowTermsOfService(!showTermsOfService);
    };

    const handleClose = () => {
        setShowCookiePolicy(false);
        setShowTermsOfService(false);
    };

    const handleOverlayClick = (event) => {
        if (event.target === event.currentTarget) {
            handleClose();
        }
    };

    return (
        <footer className="py-5 bg-dark">
            <div className="container d-flex justify-content-between align-items-center">
                <div>
                    <button className="btn btn-link text-white" onClick={toggleCookiePolicyModal}>
                        {t('footer.cookiePolicy')}
                    </button>
                    <button className="btn btn-link text-white" onClick={toggleTermsOfServiceModal}>
                        {t('footer.termsOfService')}
                    </button>
                </div>
                
                <p className="m-0 text-white">{t('footer.copyright')}</p>
                <LanguageSelectorModal/>
            </div>

            <Modal
                isOpen={showCookiePolicy}
                onRequestClose={handleClose}
                contentLabel={t('modals.cookiePolicyTitle')}
                className={styles.modalContent}
                overlayClassName={modalStyles.overlay}
                shouldCloseOnOverlayClick={true}
            >
                <div onClick={handleOverlayClick} className={modalStyles.overlay}>
                    <div className={modalStyles.modalContent}>
                        <span className={styles.close} onClick={handleClose}>&times;</span>
                        <h2>{t('modals.cookiePolicyTitle')}</h2>
                        <pre>{cookiePolicyContent}</pre>
                    </div>
                </div>
            </Modal>
           
            <Modal
                isOpen={showTermsOfService}
                onRequestClose={handleClose}
                contentLabel={t('modals.termsOfServiceTitle')}
                className={styles.modalContent}
                overlayClassName={modalStyles.overlay}
                shouldCloseOnOverlayClick={true}
            >
                <div onClick={handleOverlayClick} className={modalStyles.overlay}>
                    <div className={modalStyles.modalContent}>
                        <span className={styles.close} onClick={handleClose}>&times;</span>
                        <h2>{t('modals.termsOfServiceTitle')}</h2>
                        <pre>{termsOfServiceContent}</pre>
                    </div>
                </div>
            </Modal>
        </footer>
    );
}
