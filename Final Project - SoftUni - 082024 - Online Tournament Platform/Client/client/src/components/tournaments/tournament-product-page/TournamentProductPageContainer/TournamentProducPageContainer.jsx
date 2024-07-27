import React from "react";
import TournamentProductPageNavContainer from '../TournamentProductPageNavContainer/TournamentProductPageNavContainer';
import TournamentProductPageInfoContainer from '../TournamentProductPageInfoContainer/TournamentProductPageInfoContainer';
import TournamentProductPageGameContainer from '../TournamentProductPageGameContainer/TournamentProductPageGameContainer';
import TournamentProductPageTeamContainer from '../TournamentProductPageTeamContainer/TournamentProduactTeamContainer';

export default function TournamentProductPageContainer(){
return(

<div>
    <TournamentProductPageNavContainer/>
    <TournamentProductPageInfoContainer/>
    <TournamentProductPageTeamContainer/>
    <TournamentProductPageGameContainer/>

</div>);
}