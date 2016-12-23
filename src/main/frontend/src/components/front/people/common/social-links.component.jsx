import React from "react";
import FontAwesome  from "react-fontawesome";
import { OverlayTrigger, Tooltip } from 'react-bootstrap';

const facebookTooltip = (<Tooltip id="facebook-tooltip">Facebook</Tooltip>);
const twitterTooltip = (<Tooltip id="twitter-tooltip">Twitter</Tooltip>);
const linkedInTooltip = (<Tooltip id="linkedIn-tooltip">Linked In</Tooltip>);
const skypeTooltip = (<Tooltip id="skype-tooltip">Skype</Tooltip>);
const emailTooltip = (<Tooltip id="email-tooltip">Email</Tooltip>);
const websiteTooltip = (<Tooltip id="website-tooltip">Website</Tooltip>);
const blogTooltip = (<Tooltip id="blog-tooltip">Blog</Tooltip>);

const SocialLinks = ({details}) => {
  return (
    <div className="employee-social-links">
      {details.facebook &&
        <OverlayTrigger placement="top" overlay={facebookTooltip}>
          <a href={'https://www.facebook.com/' + details.facebook} target="_blank"><FontAwesome name='facebook'/></a>
        </OverlayTrigger>
      }
      {details.twitter &&
        <OverlayTrigger placement="top" overlay={twitterTooltip}>
          <a href={'https://twitter.com/' + details.twitter} target="_blank"><FontAwesome name='twitter'/></a>
        </OverlayTrigger>
      }
      {details.linkedIn &&
        <OverlayTrigger placement="top" overlay={linkedInTooltip}>
          <a href={'https://www.linkedin.com/in/' + details.linkedIn} target="_blank"><FontAwesome name='linkedin'/></a>
        </OverlayTrigger>
      }
      {details.skype &&
        <OverlayTrigger placement="top" overlay={skypeTooltip}>
          <a href={`skype:${details.skype}?userinfo`} target="_top"><FontAwesome name='skype'/></a>
        </OverlayTrigger>
      }
      {details.email &&
        <OverlayTrigger placement="top" overlay={emailTooltip}>
          <a href={'mailto:' + details.email} target="_top"><FontAwesome name='envelope'/></a>
        </OverlayTrigger>
      }
      {details.website &&
        <OverlayTrigger placement="top" overlay={websiteTooltip}>
          <a href={details.website} target="_blank"><FontAwesome name='university'/></a>
        </OverlayTrigger>
      }
      {details.blog &&
        <OverlayTrigger placement="top" overlay={blogTooltip}>
          <a href={details.blog} target="_blank"><FontAwesome name='bookmark'/></a>
        </OverlayTrigger>
      }
    </div>
  );
};

SocialLinks.propTypes = {
  details: React.PropTypes.object.isRequired
};

export default SocialLinks;
