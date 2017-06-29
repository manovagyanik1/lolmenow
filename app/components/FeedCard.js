/**
 * Created by madki on 29/06/17.
 */
import React, {Component} from "react";
import PropType from "prop-types";
import {
  View,
  Image,
  Text,
  StyleSheet,
  Dimensions
} from "react-native";
import Icon from "react-native-vector-icons/FontAwesome";

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginBottom: 15,
    elevation: 4,
    backgroundColor: "#FFFFFF",
    shadowColor: "black",
    shadowOpacity: 0.1,
    shadowRadius: 4,
    shadowOffset: {
      width: 0,
      height: 4
    }
  },
  cardFooter: {
    flex: 1,
    flexDirection: "row",
    height: 64,
    alignItems: "center",
    justifyContent: "space-around",
    backgroundColor: "#c5c5c5"
  },
  icon: {
  },
  share: {
  }
});

class FeedCard extends Component {
  state = {
    imgWidth: 0,
    imgHeight: 0,
  };

  componentDidMount() {
    const {data} = this.props.card;
    Image.getSize(data.mediaUrl, (width, height) => {
      // calculate image width and height
      const screenWidth = Dimensions.get('window').width;
      const scaleFactor = width / screenWidth;
      const imageHeight = height / scaleFactor;
      this.setState({imgWidth: screenWidth, imgHeight: imageHeight})
    })
  }

  render() {
    const {data} = this.props.card;
    const {imgWidth, imgHeight} = this.state;

    const getFooter = () => {
      if (imgHeight > 0) {
        return (
          <View style={styles.cardFooter}>
            <Icon name="heart-o" size={20} color="#000" style={styles.icon}/>
            <Icon name="comment" size={20} color="#000" style={styles.icon}/>
            <Icon name="share" size={20} color="#000" style={styles.share}/>
          </View>
        );
      }
    };

    return (
      <View style={styles.container}>
        <Image
          source={{uri: data.mediaUrl}}
          style={{height: imgHeight, width: imgWidth}}
          defaultSource={require("../img/placeholder.jpg")}>
        </Image>
        {getFooter()}
      </View>
    );
  };
}

FeedCard.propTypes = {
  card: PropType.object.isRequired
};

export default FeedCard;

