export default function LinkTo(event, history,url) {
    event.preventDefault();
    history.push(url);
}