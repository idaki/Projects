import React, { useContext } from 'react';
import ViewContext from '../../context/viewContext';
import styles from './MainFeedModal.module.css';
import MyTournamentsContainer from '../tournaments/my-tournaments/MyTournamentsContainer';
import FriendsContainer from '../friends/FriendsContainer';
import TeamContainer from '../teams/TeamContainer';
import WatchlistContainer from '../tournaments/watchlist/WatchListContainer';
import CreateTournamentModal from '../tournaments/tournament-create/CreateTournamentModal';
import TournamentProductPageContainer from '../tournaments/tournament-product-page/Product-Page-Container/TournamentProducPageContainer';
import EditUserContainer from '../admin-panel/edit-users/EditUserModal';
import SettingsContainer from '../settings/settings-container/SettingsModal';

export default function MainFeedModal({ userDetails }) {
  const { mainContent } = useContext(ViewContext);

  return (
    <div className={`col-md-9 ${styles.mainFeed}`}>
      <div className="card">
        <div className="card-body">
          <h5 className="card-title">{mainContent?.toUpperCase()}</h5>
        </div>
      </div>
      {mainContent === 'tournaments' && <MyTournamentsContainer />}
      {mainContent === 'friends' && <FriendsContainer />}
      {mainContent === 'teams' && <TeamContainer />}
      {mainContent === 'watchlist' && <WatchlistContainer />}
      {mainContent === 'edit-users' && <EditUserContainer />}
      {mainContent === 'settings' && <SettingsContainer userDetails={userDetails} isOpen={true} toggle={() => {}} />}
      {mainContent === 'create-tournament' && <CreateTournamentModal />}
      {mainContent === 'tournament-page' && <TournamentProductPageContainer />}
    </div>
  );
}