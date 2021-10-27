export default function LinkTo(event, history, url) {
    if(event)
        event.preventDefault();
    history.push(url);
}