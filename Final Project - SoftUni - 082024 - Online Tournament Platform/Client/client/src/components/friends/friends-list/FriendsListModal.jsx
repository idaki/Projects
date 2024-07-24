import React from "react";

export default function FriendsListModal({ friends }) {
  return (
    <div className="row">
      {friends.map((friend) => (
        <div className="col-md-6 m-b-2" key={friend.id}>
          <div className="p-10 bg-white">
            <div className="media media-xs overflow-visible">
              <a className="media-left" href="javascript:;">
                <img src={friend.avatar} alt="" className="media-object img-circle" />
              </a>
              <div className="media-body valign-middle">
                <b className="text-inverse">{friend.name}</b>
              </div>
              <div className="media-body valign-middle text-right overflow-visible">
                <div className="btn-group dropdown">
                  <a href="javascript:;" className="btn btn-default">Friends</a>
                  <a href="javascript:;" data-toggle="dropdown" className="btn btn-default dropdown-toggle"></a>
                  <ul className="dropdown-menu dropdown-menu-right">
                    <li><a href="javascript:;">Action 1</a></li>
                    <li><a href="javascript:;">Action 2</a></li>
                    <li><a href="javascript:;">Action 3</a></li>
                    <li className="divider"></li>
                    <li><a href="javascript:;">Action 4</a></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
}
