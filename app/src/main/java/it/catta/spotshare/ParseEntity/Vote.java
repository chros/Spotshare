package it.catta.spotshare.ParseEntity;

import com.parse.GetCallback;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

/**
 * Created by andrea on 20/04/15.
 */
@ParseClassName("Vote")
public class Vote extends ParseObject {

    public double getValue() {
        return getDouble("value");
    }

    public void setValue(double value) {
        put("value", value);
    }

    public void fetchCreatedBy(GetCallback<ParseUser> callback) {
        getParseObject("createdBy").fetchIfNeededInBackground(callback);
    }

    public void setCreatedBy(ParseUser creator) {
        put("createdBy", ParseObject.createWithoutData("_User", creator.getObjectId()));
    }

    public void setCreatedBy(String creatorObjectId) {
        put("createdBy", ParseObject.createWithoutData("_User", creatorObjectId));
    }

    public void fetchReferredTo(GetCallback<Spot> callback) {
        getParseObject("referredTo").fetchIfNeededInBackground(callback);
    }

    public void setReferredTo(Spot spot) {
        put("referredTo", ParseObject.createWithoutData("Spot", spot.getObjectId()));
    }

    public void setReferredTo(String spotObjectId) {
        put("referredTo", ParseObject.createWithoutData("Spot", spotObjectId));
    }

    public static void getUserVote(Spot spot, GetCallback<Vote> callback) {
        ParseQuery<Vote> query = ParseQuery.getQuery(Vote.class);
        query.whereEqualTo("createdBy", ParseUser.getCurrentUser());
        query.whereEqualTo("referredTo", spot);
        query.getFirstInBackground(callback);
    }

}
